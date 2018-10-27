
//config
var config = new Object();  //全局配置文件
var terminalArray;
var user = "";
var acode = "";
var util;
var strcheckboxsnotcomm = "";//不常用发布手段
var strcheckboxscomm = "";//常用发布手段
var strbtnmore = '<button type="button" style="margin-left:20px;" class="btn btn-primary" onclick="getMoreWay();">更多</button>';
var terminaldata;
var categorykeys = new Array();
var selectedareas = new Array();
var selectedways = new Array();
var strwarningsignal;
var areadatas;
var dengji="";
/*初始化*/
function init() {
    $.get('config/config.xml', function (xml) {
        config.url_basemap = $(xml).find('url_basemap').text();
        config.map_center = $(xml).find('map_center').text();
        config.url_getTyphoon = $(xml).find('url_getTyphoon').text();
        config.url_getPreTyphoon = $(xml).find('url_getPreTyphoon').text();
        loadMap_maxed();  //加载地图
    });
}

/*加载地图*/
function loadMap_maxed() {
    var mapParameter = new Object();
    mapParameter.center = eval(config.map_center);
    mapParameter.extent = new Object(config.map_initextent);
    var basemapUrl = config.url_basemap;
   // http://219.131.223.108:8181/TiledGoogleMap
    basemapUrl = "http://"+location.hostname+":8000/TiledGoogleMap";
    var basemapType = "roadmap";
    var tileFormat = "png";
    var busimapUrl = "";
    var maxedMap = new Map_maxed("map", mapParameter, basemapUrl, busimapUrl, basemapType, tileFormat, 4, 9, callback_loadMap);
}

/*地图加载回调函数*/
function callback_loadMap() {
    var tyid, tyname;
	var tcodes="";
    var util = new Util();
    if (util.getLocationparam().nameId != undefined) {
        tyid = util.getLocationparam().nameId;
    }
    if (util.getLocationparam().name != undefined) {
        tyname = util.getLocationparam().name;
    }
	if (util.getLocationparam().code != undefined) {
        tcodes = util.getLocationparam().code;
    }
   //ShowWarnLine();
    
    //locateExtent_geojson();
   var ids = tyid.split(",");
    var names = tyname.split(",");
	var codes = tcodes.split(",");
	var leng= ids.length;
	$("#info").html('');
	var typhcode = codes[leng-1];
	var typhname = '('+decodeURI(names[leng-1])+')';
	
    $.each(ids, function (i, id) {
        if(i==leng-1){//区分最后一个
			ShowTyphoonPath(id, names[i],0);
		}else{
			ShowTyphoonPath(id, names[i],1);
		}
    });
    if(typhcode.indexOf("TD")!=-1){
    	typhcode = typhcode.replace('TD',dengji);	
    }else{
    		typhcode =dengji+typhcode;	
    }
    
	$("#info").html(typhcode+"号"+typhname);
	 addZh();
}
//珠海（113.5795,22.2788）
function addZh(){
	var x=113.5795;
	var y=22.2788;
	var mark = new Mark();
	mark.Point_Text2(x, y,[224,8,7], "15pt","珠海","");
	mark.mark_tycircle(x,y,[42,40,28],"");
	mark.setMapView(119,20,3);
}


function ShowWarnLine() {
    var mark = new Mark();
    var points1 = [[109.4900, 17.3100], [116.1000, 17.3100], [120.9500, 20.8500] ,[122.1000, 25.0000]];
    var points2 = [[109.4900, 18.2600], [109.4900, 13.7300], [118.5000, 13.7300], [126.9300, 21.2100], [126.9300, 25.0000], [118.6700, 25.0000]];
    mark.mark_line(points1, "red", 0, 1);
    mark.mark_line(points2, "yellow", 0, 1);
    /*var text1 = ["24", "小", "时", "警", "戒", "线"];
    var text2 = ["48", "小", "时", "警", "戒", "线"];
    $.each(text1, function (i, txt) {
        mark.Point_Text(txt, 127.0000, 31.0000, "blue", "10pt", 0, -i * 15, "");
    });
    $.each(text2, function (i, txt) {
        mark.Point_Text(txt, 132.0000, 31.0000, "red", "10pt", 0, -i * 15, "");
    });*/
}


function ShowTyphoonPath(tyid, tyname,type) {
    var marker = new Mark();
    var preStartpt = [];
    var preStarttime;
    $.support.cors = true;
    //台风实况数据
    $.ajax({
        type: "get",//使用get方法访问后台
        jsonp:"callback",
        dataType: "jsonp",//返回json格式的数据
        data: {
            nameId: tyid,
            name: tyname
        },
        async: false,
        url: config.url_getTyphoon,
        //dataType: "jsonp",  
        //jsonp: "callback", 
        complete:function(xmlreq,textStatus){
        	var s=xmlreq;
        	var p=textStatus;
        },
        success: function (datas) {
            if (datas == "") {
                return false;
            }

            var iconUrl = "img/close_e_o.png";
            var ptStart = [];
            var w = 10;
            var h = 10;
            var grade = "";
            $.each(datas, function (i, data) {
                //setTimeout(function () {
                var x = data.longitude;
                var y = data.latitude;
                var windforce=data.windforce;	
								if(windforce>=18){
									data.windforce=17;
								}
				var leng = datas.length;
                var wspeed = parseFloat(data.windspeed);
                if (wspeed > 10.8 && wspeed <= 17.1) {
                    iconUrl = "img/6-7.png";
                    grade = "热带低压";
                } else if (wspeed > 17.2 && wspeed <= 24.4) {
                    iconUrl = "img/8-9.png";
                    grade = "热带风暴";
                } else if (wspeed > 24.5 && wspeed <= 32.6) {
                    iconUrl = "img/10-11.png";
                    grade = "强热带风暴";
                } else if (wspeed > 32.7 && wspeed <= 41.4) {
                    iconUrl = "img/12-13.png";
                    grade = "台风";
                } else if (wspeed > 41.5 && wspeed <= 50.9) {
                    iconUrl = "img/14-15.png";
                    grade = "强台风";
                } else if (wspeed > 51.0 ) {
                    iconUrl = "img/16-17.png";
                    grade = "超强台风";
                }
                if (i == datas.length - 1) {
                	dengji= grade;
                    iconUrl = "img/typhoon.gif";
                    w = 60;
                    h = 50;
                    if (data.rr07 != "") {
                        marker.mark_circle(x, y, parseFloat(data.rr07));
                    }
                    preStarttime = data.time;

                    preStartpt.push(x);
                    preStartpt.push(y);
                    var title =data.name;
                    
                    var day1 = data.time.split(" ")[0].split("-")[2]+"日";
                    var hour1 = data.time.split(" ")[1].split(":")[0]+"时";
                    var tt = day1+hour1;
                    var title1 =tt+","+grade;
                    //marker.Point_Text(title,data.longitude-3, data.latitude, [0,0,0], "12pt",0,0,"");
                    //marker.Point_Text(title1,data.longitude-5, data.latitude-1, [0,0,0], "12pt",0,0,"");
                }
				if(type==0){//
					if(i==leng-1){
						var showLastInfo="<table style=\"margin-bottom:0px;\" class=\"table table-hover table-bordered table-condensed\">" +
						"<tr><td width='30%'>名称</td><td>${name}</td></tr>" +
						"<tr><td width='30%'>时间</td><td>${time}</td></tr>" +
						"<tr><td width='30%'>当前位置</td><td>东经${longitude}  北纬${latitude}</td></tr>" +
						"<tr><td width='30%'>中心气压</td><td>${pressure}百帕</td></tr>" +
						"<tr><td width='30%'>最大风速</td><td>${windspeed}米/秒</td></tr>" +
						"<tr><td width='30%'>最大风力</td><td>${windforce}级</td></tr>" +
						"<tr><td width='30%'>等级</td><td>" + grade + "</td></tr>" +
						
						"<tr><td width='30%'>七级半径</td><td>${rr07}公里</td></tr>" +
						"<tr><td width='30%'>距离珠海</td><td>${distances}公里</td></tr>" +
						"</table><div style=\"width:100%;text-align:right;\" onclick='closeLastInfo()'><img src='img/close.png'/></div>";
						//"<tr><td width='30%'>移动速度</td><td>${windspeed}米/秒</td></tr>" +
						showLastInfo = showLastInfo.replace('${name}',data.name);
						showLastInfo = showLastInfo.replace('${time}',data.time);
						showLastInfo = showLastInfo.replace('${longitude}',data.longitude);
						showLastInfo = showLastInfo.replace('${latitude}',data.latitude);
						showLastInfo = showLastInfo.replace('${pressure}',data.pressure);
						showLastInfo = showLastInfo.replace('${gust}',data.gust);
						showLastInfo = showLastInfo.replace('${windforce}',data.windforce);
						showLastInfo = showLastInfo.replace('${windspeed}',data.windspeed);
						showLastInfo = showLastInfo.replace('${rr07}',data.rr07);
						showLastInfo = showLastInfo.replace('${distances}',data.distances);
						$('#showLastinfo').html('');
						$('#showLastinfo').html(showLastInfo);
						//$('#showLastinfo').show();
					}
				}

                var ptEnd = [];
                ptEnd.push(x);
                ptEnd.push(y);
                var showAttrHtml = "<!--<div style='text-align:center;font-size:20px;'>${name}</div>--><table class=\"table table-hover table-bordered table-condensed\">" +
                "<tr><td width='30%'>时间</td><td>${time}</td></tr>" +
                "<tr><td width='30%'>当前位置</td><td>东经${longitude}  北纬${latitude}</td></tr>" +
                "<tr><td width='30%'>中心气压</td><td>${pressure}百帕</td></tr>" +
                "<tr><td width='30%'>最大风速</td><td>${windspeed}米/秒</td></tr>" +
                "<tr><td width='30%'>风力</td><td>${windforce}级</td></tr>" +
                "<tr><td width='30%'>等级</td><td>" + grade + "</td></tr>" +
                //"<tr><td width='30%'>移动速度</td><td>${windspeed}米/秒</td></tr>" +
                "<tr><td width='30%'>七级半径</td><td>${rr07}公里</td></tr>" +
                "<tr><td width='30%'>距离珠海</td><td>${distances}公里</td></tr>" +
                "</table>" +
                "</table>";

                if (ptStart.length > 0) {
                    var points = [];
                    points.push(ptStart);
                    points.push(ptEnd);
                    marker.mark_line(points, "gray", 0, 2);
                    //CenterAt(x, y);
                    ShowGraphicWithPop(x, y, "", data.name + "," + data.time + "," + grade);
                }

                marker.mark_icon(this.map, x, y, iconUrl, w, h, data, data.name, showAttrHtml);
                //marker.mark_commonPoint(this.map, data.longitude, data.latitude, data, data.name, showAttrHtml,"red");
                ptStart = [];
                ptStart.push(x);
                ptStart.push(y);
                //}, 3000);
            });

            ShowTyphoonPop(this.map);
        }
    });

    //台风预报数据
    $.ajax({
        type: "get",//使用get方法访问后台
        jsonp:"callback",
        dataType: "jsonp",//返回json格式的数据
        data: {
            nameId: tyid,
            name: tyname,
            lastTime: preStarttime
        },
        async: false,
        url: config.url_getPreTyphoon,
        success: function (datas) {
            if (datas == "") {
                return false;
            }

            var iconUrl = "img/close_e_o.png";

            var w = 15;
            var h = 20;
            var grade = "";
            $.each(datas, function (j, predata) {
                var pres = predata.data;
                var lineColor = "red";
                //if (predata.orgname == "北京") {
                //    lineColor = "red";
                //} else if (predata.orgname == "香港") {
                //    lineColor = "yellow";
                //} else if (predata.orgname == "日本") {
                //    lineColor = "green";
                //}
				//$("#org").html("");
				//$("#org").html(predata.orgname);
                var ptStart = [];
				if(pres){
					$.each(pres, function (i, data) {
						 var windforce=data.windforce;	
								if(windforce>=18){
									data.windforce=17;
								}
						var wspeed = parseFloat(data.windspeed);
						if (wspeed > 10.8 && wspeed <= 17.1) {
							iconUrl = "img/01.png";
							grade = "热带低压";
						} else if (wspeed > 17.2 && wspeed <= 24.4) {
							iconUrl = "img/02.png";
							grade = "热带风暴";
						} else if (wspeed > 24.5 && wspeed <= 32.6) {
							iconUrl = "img/03.png";
							grade = "强热带风暴";
						} else if (wspeed > 32.7 && wspeed <= 41.4) {
							iconUrl = "img/04.png";
							grade = "台风";
						} else if (wspeed > 41.5 && wspeed <= 50.9) {
							iconUrl = "img/05.png";
							grade = "强台风";
						} else if (wspeed > 51.0 ) {
							iconUrl = "img/06.png";
							grade = "超强台风";
						}

						var ptEnd = [];
						ptEnd.push(data.longitude);
						ptEnd.push(data.latitude);
						var showAttrHtml = "<!--<div style='text-align:center;font-size:20px;'>${name}</div>--><table class=\"table table-hover table-bordered table-condensed\">" +
						"<tr><td width='30%'>时间</td><td>${time}</td></tr>" +
						"<tr><td width='30%'>当前位置</td><td>东经${longitude}  北纬${latitude}</td></tr>" +
						"<tr><td width='30%'>中心气压</td><td>${pressure}百帕</td></tr>" +
						"<tr><td width='30%'>最大风速</td><td>${windspeed}米/秒</td></tr>" +
						"<tr><td width='30%'>风力</td><td>${windforce}级</td></tr>" +
						"<tr><td width='30%'>等级</td><td>" + grade + "</td></tr>" +
						//"<tr><td width='30%'>移动速度</td><td>${windspeed}米/秒</td></tr>" +
						"<tr><td width='30%'>七级半径</td><td>${rr07}公里</td></tr>" +
						"<tr><td width='30%'>距离珠海</td><td>${distances}公里</td></tr>" +
						"</table>" +
						"</table>";
						var points = [];
						if (ptStart.length > 0) {
							points.push(ptStart);
						} else {
							//points.push(preStartpt);
						}
						var pretext=data.LEADTIME+"h";
						//if(pretext=='undefinedh'){
							pretext="";
						//}
						if(i==0){
								iconUrl="";
							}
						points.push(ptEnd);
						marker.mark_line(points, lineColor, 1, 2);
						marker.mark_icon(this.map, data.longitude, data.latitude, iconUrl, w, h, data, data.name, showAttrHtml);
						 //预报标注
						marker.Point_Text(pretext,data.longitude-0.2, data.latitude, [10,152,255], "12pt",0,0,"");
						//marker.mark_commonPoint(this.map, data.longitude, data.latitude, data, data.name, showAttrHtml,"red");
						ptStart = [];
						ptStart.push(data.longitude);
						ptStart.push(data.latitude);
					});
				}
            });

            //ShowTyphoonPop(this.map);
        }
    });
}

/*加载业务数据点并绘制在地图上*/
function markTerminal_ajax() {
    var util = new Util();
    var user = "";
    if (util.getLocationparam().yjptusername != undefined) {
        user = util.getLocationparam().yjptusername;
    }
    $.ajax({
        type: "get",//使用get方法访问后台
        dataType: "json",//返回json格式的数据
        url: config.url_getWarnData,
        success: function (warningdatas) {//msg为返回的数据，在这里做数据绑定
            //if(warningdatas==""){alert("未查询到数据")}
            var mark = new Mark();
            var i = 0;//标识加载进度
            var iconUrl = "";
            var x, y;
            $.each(warningdatas, function (i, warningdata) {

                $.each(areadatas, function (i, areadata) {
                    if (areadata.areaCode == warningdata.areacode) {

                        x = parseFloat(areadata.longitude) + GetRandomNum(-0.005, 0.005);
                        y = parseFloat(areadata.latitude) + GetRandomNum(-0.005, 0.005);
                        iconUrl = warningdata.picUrl;
                        var title = "预警信息";
                        var showAttrHtml = "<table class=\"table table-hover table-bordered table-condensed\">" +
                "<tr><td width='30%'>灾害类型</td><td>${zaizhong}</td></tr>" +
                "<tr><td width='30%'>状态</td><td>${color}</td></tr>" +
                "<tr><td width='30%'>级别</td><td>${grade}</td></tr>" +
                "<tr><td width='30%'>内容</td><td>${content}</td></tr>" +
                "</table>" +
                "</table>";

                        mark.mark_icon(this.map, x, y, iconUrl, 16, 16, warningdata, title, showAttrHtml);


                        i++;
                        p = (i / warningdatas.length) * 100;
                    }
                });


                //progress_set(p);

            });
        }
    });
}

/*
*获取随机数
*/
function GetRandomNum(Min, Max) {
    var Range = Max - Min;
    var Rand = Math.random();
    return Rand * Range;
}

/*绘制区域边界*/
function locateExtent_geojson() {
    //if (util.getLocationparam().userid != undefined) {
    //    user = util.getLocationparam().userid;
    //}
    //if (util.getLocationparam().areacode != undefined) {
    //    acode = util.getLocationparam().areacode;
    //}
    //alert("markTerminal_ajax");
    //if (user == "admin") {
    acode = "120000";
    //}
    $.ajax({
        type: "get",//使用get方法访问后台
        dataType: "json",//返回json格式的数据
        url: "data/geojson/zh.geojson",
        success: function (terminalArray) {//msg为返回的数据，在这里做数据绑定
            //alert(terminalArray.features);
            var mark = new Mark();
            $.each(terminalArray.features, function (i, feature) {
                //if (feature.properties.CITYCODE == acode) {//城市
                //    //alert(feature.geometry.coordinates);
                //    var coordinates = feature.geometry.coordinates;
                //    var text = feature.properties.RNAME;
                //    mark.mark_polygon(this.map, coordinates, text);
                //    return;
                //} else if (feature.properties.ADCODE99 == acode) {//省、县
                //    var coordinates = feature.geometry.coordinates;
                //    var text = feature.properties.NAME99;  //县为NAME99；
                //    if (text == "") {
                //        text = feature.properties.NAME;  //省为NAME；
                //    }
                //    mark.mark_polygon(this.map, coordinates, text);
                //}

                var coordinates = feature.geometry.coordinates;
                var text = feature.properties.RNAME;
                mark.mark_polygon(this.map, coordinates, text);
            });
        }
    });
}
/*进度条*/
function progress_set(p) {
    //alert(p);
    $("#progress_bar").css({ "width": p + "%" });
    if (p > 99) {
        progress_hide();
    }
}
function progress_hide() {
    //alert("progress_hide");
    stopCount();
    $("#progress_bar").css({ "width": 1 + "%" });
    $("#progress").hide();
}
function progress_show() {
    //alert("progress_show");
    timedCount();
    $("#progress_bar").css({ "width": 1 + "%" });
    $("#progress").show();
}
var p = 1;
var t;  //定时器
function timedCount() {
    p = p + 1;
    t = setTimeout("timedCount()", 50);
    progress_set(p);
}
function stopCount() {
    clearTimeout(t);
}



/*筛选不同状态数据*/
function filterAbnormalterminal() {
    if (document.getElementById("checkbox_terminalState").checked) {
        $('#table').scope().terminals = $('#table').scope().terminals_all;
        $('#table').scope().$digest();
    } else {
        $('#table').scope().terminals = $('#table').scope().terminals_normal;
        $('#table').scope().$digest();
    }
}

/*发布消息时在地图上画框查询并将查询结果显示到弹出框*/
function setTerminal() {
    //toolbar
    var toolbar_graphicQuery = new Toolbar_graphicQuery(this.toolbar, function (data) {
        //data
        //所有的
        $('#table').scope().terminals_all = data;
        //除去不正常正常的
        var terminals_normal = new Array();
        for (var i = 0; i < data.length; i++) {
            var terminal = data[i];
            if (terminal.terminalstate != "不正常") {
                terminals_normal.push(terminal);
            }
            /*start筛选出选择点的区县*/
            if (terminal.countycode != "") {
                if (selectedareas.length == 0) {

                    var areaobj = new Object();
                    areaobj.name = terminal.countyname;
                    areaobj.code = terminal.countycode;
                    selectedareas.push(areaobj);

                } else {
                    var k = 0;
                    $.each(selectedareas, function (j, area) {
                        if (terminal.countyname == area.name) {
                            k++;
                            return;
                        }
                    });
                    if (k == 0) {
                        var areaobj = new Object();
                        areaobj.name = terminal.countyname;
                        areaobj.code = terminal.countycode;
                        selectedareas.push(areaobj);
                    }
                }
            }
            /*end筛选出选择点的区县*/


            if (categorykeys.indexOf(terminal.categoryKey) == -1) {
                categorykeys.push(terminal.categoryKey);
            }
        }

        if (selectedareas.length > 0) {
            var strcheckboxs = "<strong>区域：</strong>";
            $.each(selectedareas, function (i, area) {
                strcheckboxs += '<label style="margin-left:15px"><input type="checkbox" checked id="' + area.code + '" value="' + area.name + '" onclick="selectArea(this.id)"> ' + area.name + '</label>';
            });
            $("#divCheckbox").html(strcheckboxs);
        }

        $("#divMsg").html("");
        $("#ipStartDate").val("");
        $("#ipEndDate").val("24");
        //alert(terminals_normal.length);
        $('#table').scope().terminals_normal = terminals_normal;
        //alert($('#table').scope().terminals_normal.length);
        $("#textaren_xxfb").val("");
        //document.getElementById("checkbox_terminalState").checked = true;
        $('#table').scope().terminals = $('#table').scope().terminals_all;
        $('#table').scope().$digest();
        //toolbar.deactive;
        this.toolbar.deactivate();
        //modal
        //alert($('#table').scope().terminals.length);
        $('#modal_xxfb').modal({});
    });
}

/*显示不同终端的数据*/
function selectTerminal(terminalType) {
    var i = 0;
    var strtype;

    progress_show();
    //clean
    var mark = new Mark();
    mark.clean_mark(this.map);
    if (terminalType == 2) {//全部
        $.each(terminaldata, function (i, terminal) {
            markPoint(terminal, mark);
            i++;
            p = (i / terminaldata.length) * 100;
            progress_set(p);
        });
    } else {
        if (terminalType == 0) {
            strtype = "显示屏";
        } else if (terminalType == 1) {
            strtype = "大喇叭";
        }
        else if (terminalType == 1003) {
            strtype = "多媒体";
        }
        $.each(terminaldata, function (i, terminal) {
            if (terminal.terminalstyle == terminalType || terminal.terminalstyle == strtype) {
                markPoint(terminal, mark);
            }
            i++;
            p = (i / terminaldata.length) * 100;
            progress_set(p);
        });
    }
}


function customersController($scope) {
    $scope.terminals = [];
}

/*隐藏弹出框时恢复点样式*/
function recoverMark() {
    var mark = new Mark();
    mark.symbol_terminal(this.toolbarData_currentSelect)
    //alert(this.toolbarData_currentSelect.length);
}

/*发布消息时获取消息数据并调用发布数据的接口*/
function send_xxfb() {
    //alert("send_xxfb");
    //terminals from toolbar
    var terminals = [];
    terminals = this.toolbarData_currentSelect;
    //alert(terminals[0].name);
    //content_xxfb
    var xxfbContext = "";
    xxfbContext = document.getElementById("textaren_xxfb").value;
    //alert(xxfbContext);
    //param
    //var util = new Util();
    //var user = "";
    //if (util.getLocationparam().yjptusername != undefined) {
    //    user = util.getLocationparam().yjptusername;
    //}
    //var confid = "";

    //var uriQuery = location.search; //获取url中"?"符后的字串
    //var theRequest = new Object();
    //if (uriQuery.indexOf("?") != -1) {
    //    var str = uriQuery.substr(1);
    //    strs = str.split("&");
    //    for (var i = 0; i < strs.length; i++) {
    //        var p = strs[i].indexOf("=");
    //        if (p != -1) {
    //            var argname = strs[i].substring(0, p);
    //            var value = strs[i].substring(p + 1);
    //            theRequest[argname] = value;
    //        }
    //        // theRequest[strs[i].split("=")[0]]=(strs[i].split("=")[1]);

    //    }
    //}
    //confid = theRequest["yjptconfid"];

    /* if(util.getLocationparam().yjptconfid!=undefined){
         confid = util.getLocationparam().yjptconfid;
     }*/
    var templateid = "";
    var ihtimestamp = "";
    //ihtimestamp = theRequest["yjpttime"];

    var terminalobjarr = new Array();
    var terminal = ""; //终端序列号 多个用，隔开
    for (var i = 0; i < terminals.length; i++) {
        var terminalobj = new Object();
        terminalobj.serviceUserId = terminals[i].attributes.serviceUserId;
        terminalobj.name = terminals[i].attributes.name;
        terminalobj.categoryKey = terminals[i].attributes.categoryKey;
        terminalobj.channelKey = terminals[i].attributes.channelKey;
        terminalobj.serinalno = terminals[i].attributes.serinalno;
        terminalobjarr.push(terminalobj);
    }
    //if (terminal != "") {
    //    terminal = terminal.substr(0, terminal.length - 1)//去掉最后的逗号
    //}
    var strareacodes = "";//选择的区域
    $.each(selectedareas, function (i, area) {
        if (i == 0) {
            strareacodes += area.code;
        } else {
            strareacodes += "," + area.code;
        }
    });
    var resultboj = new Object();
    resultboj.content = xxfbContext;
    resultboj.areacodes = strareacodes;
    resultboj.terminals = terminalobjarr;
    resultboj.planPublishTime = $("#ipStartDate").val();
    resultboj.warnSignal = strwarningsignal;
    resultboj.expDate = $("#ipEndDate").val();
    resultboj.categoryKeys = selectedways.toString();
    var strtaskinfo = JSON.stringify(resultboj);
    $.ajax({
        url: config.url_getPublishMsg,
        data: {
            userid: user,
            areacode: acode,
            taskinfo: strtaskinfo
        },
        type: "post",
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("调用服务失败");
        },
        success: function (data, textStatus) {
            if ('success' == data) {
                alert("发布成功！");
                $('#modal_xxfb').modal('hide');
            } else {
                alert('发布失败！');
            }

        },
        complete: function (XMLHttpRequest, textStatus) {
            //alert("complete");
        }
    });
}

/*地图类型选择*/
function selectBasemap(baseMap) {
    var map = new Map();
    var basemapUrl = config.url_basemap;
    if (baseMap == "satellite") {
        var basemapType = "satellite";
        var tileFormat = "jpg";
        map.switchBasemap(this.map, this.baseLayer, basemapUrl, basemapType, tileFormat);
    }
    if (baseMap == "roadmap") {
        var basemapType = "roadmap";
        var tileFormat = "png";
        map.switchBasemap(this.map, this.baseLayer, basemapUrl, basemapType, tileFormat);
    }
    if (baseMap == "terrain") {
        var basemapType = "terrain";
        var tileFormat = "jpg";
        map.switchBasemap(this.map, this.baseLayer, basemapUrl, basemapType, tileFormat);
    }
}

function crosdomtest() {
    user = "tianjin";
    acode = "120000";
    $.ajax({
        type: "get",//使用get方法访问后台
        dataType: "xml",//返回json格式的数据
        data: {
            url: config.url_getTerminal,
            userid: user,
            areacode: acode
        },
        url: "Handler/DataHandler.ashx?Action=GetData",
        success: function (data) {//msg为返回的数据，在这里做数据绑定
            var obj = JSON.parse($(data).find("return").first().text());
            alert(obj[0].countyname);
        },
        error: function (x, e) {
            alert("请求数据失败");
        }
    });
}


/*区域选择*/
function selectArea(id) {
    var strmethod = "delete";
    if ($("#" + id).is(':checked')) {
        var areaobj = new Object();
        areaobj.code = id;
        areaobj.name = $("#" + id).val();
        selectedareas.push(areaobj);
    } else {
        $.each(selectedareas, function (i, area) {
            if (area.code == id) {
                selectedareas.splice(i, 1);
            }
        });
    }
}

function selectAllText() {
    $("#ipEndDate").select();
}


function textChange() {
    if ($("#textaren_xxfb").val().trim() == "") {
        $("#btnPublish").attr("disabled", true);
    } else {
        $("#btnPublish").attr("disabled", false);
    }
}
//$("#ckbPublishWaysNotComm").hide();
/*点击发布*/
$("#btnPublishMsg").click(function () {
    //setTerminal();
    crosdomtest();
});
/*隐藏弹出框*/
$('#modal_xxfb').on('hidden.bs.modal', function (e) {
    recoverMark();
});
function closeLastInfo(){
	$('#showLastinfo').hide();
	$('#showLastinfo_show').show();
}
function showLastInfo(){
	$('#showLastinfo').show();
	$('#showLastinfo_show').hide();
}