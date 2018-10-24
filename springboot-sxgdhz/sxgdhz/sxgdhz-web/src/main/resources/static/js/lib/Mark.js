/**
 * Created by ssdzht on 2014/11/4.
 *
 * 地图标注操作类
 *
 */
function Mark() {

}
/*创建普通点
* eg:
*    var x = 108;
     var y = 23.5;
     var iconUrl = "imgi_radar.png";
     var iconWidth = 28;
     var iconHeight = 28;
     var mark = new Mark();
     mark.mark_commonPoint(this.map,x,y);
* */
Mark.prototype.mark_commonPoint = function (map, longitude, latitude, attr, showTitle, showAttrHtml, color) {
    require([
        "esri/geometry/Point", "esri/symbols/SimpleMarkerSymbol",
        "esri/Color", "esri/InfoTemplate", "esri/graphic", "esri/layers/GraphicsLayer"
    ], function (Point, SimpleMarkerSymbol, Color, InfoTemplate, Graphic, GraphicsLayer) {
        var layer = this.map.getLayer("ptLayer");
        if (layer == null) {
            layer = new GraphicsLayer({ id: "ptLayer" });
            this.map.addLayer(layer);
        }
        var pt = new Point(longitude, latitude);
        var sms = new SimpleMarkerSymbol().setStyle(
            SimpleMarkerSymbol.STYLE_CIRCLE).setColor(
            new Color(color));
        var infoTemplate = new InfoTemplate(showTitle, showAttrHtml);
        var graphic = new Graphic(pt, sms, attr, infoTemplate);
        layer.add(graphic);
    });
}
/*创建图标点
* eg:
*    var x = 108;
     var y = 23.5;
     var iconUrl = "imgi_radar.png";
     var iconWidth = 28;
     var iconHeight = 28;
     var mark = new Mark();
     mark.mark_iconPoint(this.map,x,y,iconUrl,iconWidth,iconHeight);
*
* */
Mark.prototype.mark_iconPoint = function (map, x, y, iconUrl, iconWidth, iconHeight) {
    require([
        "esri/geometry/Point", "esri/symbols/SimpleMarkerSymbol", "esri/symbols/PictureMarkerSymbol", "esri/symbols/SimpleLineSymbol",
        "esri/Color", "esri/InfoTemplate", "esri/graphic"
    ], function (Point, SimpleMarkerSymbol, PictureMarkerSymbol, SimpleLineSymbol, Color, InfoTemplate, Graphic) {
        //alert(iconUrl);
        var pt = new Point(x, y);
        var pictureMarkerSymbol = new PictureMarkerSymbol(iconUrl, iconWidth, iconHeight);
        var attr = { "longitude": x, "latitude": y, "ID": "001" };
        var infoTemplate = new InfoTemplate("信息", "经度: ${longitude} <br/>纬度: ${latitude}");
        var graphic = new Graphic(pt, pictureMarkerSymbol, attr, infoTemplate);
        this.map.graphics.add(graphic);
    });
}
/*创建图标多点*/
Mark.prototype.mark_iconMultiPoint = function (map, XYArray, iconUrl, iconWidth, iconHeight) {
    require([
        "esri/geometry/Multipoint", "esri/symbols/SimpleMarkerSymbol", "esri/symbols/PictureMarkerSymbol", "esri/symbols/SimpleLineSymbol",
        "esri/Color", "esri/InfoTemplate", "esri/graphic"
    ], function (Multipoint, SimpleMarkerSymbol, PictureMarkerSymbol, SimpleLineSymbol, Color, InfoTemplate, Graphic) {
        //alert(iconUrl);
        var mpJson = { "points": XYArray, "spatialReference": ({ " wkid": 4326 }) };
        var multipoint = new Multipoint(mpJson);

        var pictureMarkerSymbol = new PictureMarkerSymbol(iconUrl, iconWidth, iconHeight);
        var attr = { "longLat": XYArray };
        var infoTemplate = new InfoTemplate("信息", "经纬度序列: ${longLat}");
        var graphic = new Graphic(multipoint, pictureMarkerSymbol, attr, infoTemplate);
        this.map.graphics.add(graphic);
    });
}
/*根据xy数组创建多点*/
Mark.prototype.mark_iconArrayPoint = function (map, XYArray, iconUrl, iconWidth, iconHeight) {
    for (var i = 0; i < XYArray.length; i++) {
        var x = XYArray[i][0];
        var y = XYArray[i][1];
        this.mark_iconPoint(map, x, y, iconUrl, iconWidth, iconHeight);
    }
}
/*标准的根据对象数组创建标注
*
* showTitle:属性标题字符串
* showAttr:属性展示内容,支持hlml标签，变量写成${var}，其中var来自元素属性(attr)
* */
Mark.prototype.mark_icon = function (map, x, y, iconUrl, iconWidth, iconHeight, attr, showTitle, showAttrHtml) {
    //alert("make_icon");
    //graphic
    require([
        "esri/geometry/Point", "esri/symbols/SimpleMarkerSymbol", "esri/symbols/PictureMarkerSymbol", "esri/symbols/SimpleLineSymbol",
        "esri/Color", "esri/InfoTemplate", "esri/graphic"
    ], function (Point, SimpleMarkerSymbol, PictureMarkerSymbol, SimpleLineSymbol, Color, InfoTemplate, Graphic) {
        //alert(iconUrl);
        var pt = new Point(x, y);
        var pictureMarkerSymbol = new PictureMarkerSymbol(iconUrl, iconWidth, iconHeight);
        var iconAttr = attr;
        var infoTemplate = new InfoTemplate(showTitle, showAttrHtml);
        var graphic = new Graphic(pt, pictureMarkerSymbol, iconAttr, infoTemplate);
        this.map.graphics.add(graphic);
    });
}

Mark.prototype.MarkPoitWithLyName = function (map, x, y, iconUrl, iconWidth, iconHeight, attr, showTitle, showAttrHtml, layername) {
    //alert("make_icon");
    //graphic
    require([
        "esri/geometry/Point", "esri/symbols/SimpleMarkerSymbol", "esri/symbols/PictureMarkerSymbol", "esri/symbols/SimpleLineSymbol",
        "esri/Color", "esri/InfoTemplate", "esri/graphic", "esri/layers/GraphicsLayer"
    ], function (Point, SimpleMarkerSymbol, PictureMarkerSymbol, SimpleLineSymbol, Color, InfoTemplate, Graphic, GraphicsLayer) {
        //alert(iconUrl);
        var gLayer;
        var layer = this.map.getLayer(layername);
        if (layer != null) {
            gLayer = layer;
        } else {
            gLayer = new GraphicsLayer({ id: layername });
            this.map.addLayer(gLayer);
        }
        var pt = new Point(x, y);
        var pictureMarkerSymbol = new PictureMarkerSymbol(iconUrl, iconWidth, iconHeight);
        var iconAttr = attr;
        var infoTemplate = new InfoTemplate(showTitle, showAttrHtml);
        var graphic = new Graphic(pt, pictureMarkerSymbol, iconAttr, infoTemplate);
        gLayer.add(graphic);
    });
}

Mark.prototype.markPointWithoutInfoTem = function (map, x, y, iconUrl, iconWidth, iconHeight, attr) {
    //alert("make_icon");
    //graphic
    require([
        "esri/geometry/Point", "esri/symbols/SimpleMarkerSymbol", "esri/symbols/PictureMarkerSymbol", "esri/symbols/SimpleLineSymbol",
        "esri/Color", "esri/graphic"
    ], function (Point, SimpleMarkerSymbol, PictureMarkerSymbol, SimpleLineSymbol, Color, Graphic) {
        //alert(iconUrl);
        var pt = new Point(x, y);
        var pictureMarkerSymbol = new PictureMarkerSymbol(iconUrl, iconWidth, iconHeight);
        var iconAttr = attr;
        var graphic = new Graphic(pt, pictureMarkerSymbol, iconAttr);
        this.map.graphics.add(graphic);
    });
}

Mark.prototype.clean_mark = function (map) {
    map.graphics.clear();
}
Mark.prototype.symbol_terminal = function (graphics) {
    require(["esri/symbols/PictureMarkerSymbol"], function (PictureMarkerSymbol) {
        for (var i = 0; i < graphics.length; i++) {
            var graphic = graphics[i];
            var terminal = graphic.attributes;
            var iconUrl = "img/channel/" + terminal.channel + "-0.png";
            var pictureMarkerSymbol = new PictureMarkerSymbol(iconUrl, 16, 16);
            graphic.setSymbol(pictureMarkerSymbol);
        }
    });
}


function SetSymbolByLayer(id, state, tremid) {
    require(["esri/symbols/PictureMarkerSymbol", "esri/layers/GraphicsLayer"], function (PictureMarkerSymbol, GraphicsLayer) {
        var layer = this.map.getLayer("glayer" + id);
        var graphics = layer.graphics;
        var iconUrl = "img/channel/" + id + "-" + state + ".png";
        var graphic;
        for (var i = 0; i < graphics.length; i++) {
            if (graphics[i].attributes.serial == tremid) {
                graphic = graphics[i];
                graphic.attributes.sendState = GetSendStateName(state);
                var pictureMarkerSymbol = new PictureMarkerSymbol(iconUrl, 16, 16);
                graphic.setSymbol(pictureMarkerSymbol);
            }

        }
    });
}

function GetSendStateName(id) {
    switch (id) {
        case "0":
            return "待处理";
            break;
        case "1":
            return "待发送";
            break;
        case "2":
            return "发送成功";
            break;
        case "3":
            return "发送失败";
            break;
        case "4":
            return "撤销";
            break;
        case "5":
            return "数据过期 停止发送";
            break;
    }
}

Mark.prototype.mark_line = function (points, color, linetype,size) {
    require([
        "esri/graphic",
        "esri/layers/GraphicsLayer",
        "esri/geometry/Polyline",
        "esri/symbols/SimpleLineSymbol",
        "esri/Color"], function (
        Graphic, GraphicsLayer, Polyline, SimpleLineSymbol, Color
        ) {
            var lineLayer = this.map.getLayer("lineLayer");
            if (lineLayer == null) {
                lineLayer = new GraphicsLayer({ id: "lineLayer" });
                this.map.addLayer(lineLayer,21);
            }
            var type = "";
            if (linetype == 0) {
                type = SimpleLineSymbol.STYLE_SOLID;
            } else if (linetype==1) {
                type = SimpleLineSymbol.STYLE_DASHDOTDOT;
            }
            else {
                type = SimpleLineSymbol.STYLE_DASH;
            }
            var polyLine = new Polyline(points);
            var lineGraphic = new Graphic(polyLine, new SimpleLineSymbol(
                //SimpleLineSymbol.STYLE_DASHDOT,//一个点一条线相间
                //SimpleLineSymbol.STYLE_LONGDASHDOT,
                //SimpleLineSymbol.STYLE_SHORTDASHDOT,//虚线

                //SimpleLineSymbol.STYLE_DASHDOTDOT,//两个点一条线相间
                //SimpleLineSymbol.STYLE_SHORTDASHDOTDOT,//虚线

                //SimpleLineSymbol.STYLE_DOT,//点线
                //SimpleLineSymbol.STYLE_SHORTDOT,//短点线

                //SimpleLineSymbol.STYLE_NULL,//无样式
                //SimpleLineSymbol.STYLE_SOLID,//实线

                //SimpleLineSymbol.STYLE_DASH,//虚线
                //SimpleLineSymbol.STYLE_LONGDASH,//长虚线
                //SimpleLineSymbol.STYLE_SHORTDASH,//短虚线
                type,
                new Color(color),
                size
                ));
            lineLayer.add(lineGraphic);
        });
}


Mark.prototype.mark_tycircle = function (longitude, latitude,color,layerid) {
    require([
        "esri/graphic",
        "esri/layers/GraphicsLayer",
        "esri/geometry/Polyline",
        "esri/geometry/Circle",
        "esri/geometry/Point",
        "esri/symbols/TextSymbol",
        "esri/symbols/SimpleLineSymbol",
        "esri/symbols/SimpleFillSymbol",
        "esri/symbols/Font",
        "esri/Color"], function (
        Graphic, GraphicsLayer, Polyline, Circle, Point,TextSymbol,SimpleLineSymbol, 
        SimpleFillSymbol,Font, Color
        ) {
        	var gLayer;
            if (layerid == "") {
                gLayer = this.map.graphics;
            } else {
                gLayer = this.map.getLayer(layerid);
                if (gLayer == null) {
                    gLayer = new GraphicsLayer({ id: layerid });
                    this.map.addLayer(gLayer);
                }
            }
          var circleSymb = new SimpleFillSymbol(
          SimpleFillSymbol.STYLE_SOLID,
          new SimpleLineSymbol(
            SimpleLineSymbol.STYLE_SOLID,
            new Color([105, 105, 105]),
            1
          ), new Color([243, 146, 11, 0.0])
        );
        
        var linsym=new SimpleLineSymbol(SimpleLineSymbol.STYLE_DASHDOT).setColor(new Color(color)).setWidth(1.5);
        
			var radius1=400;
			var radius2=800;
            var circle = new Circle({
                center: [longitude, latitude],
                radius: radius1,
                geodesic: true,
                numberOfPoints:1000,
                radiusUnit: "esriKilometers"
            });
            
            var lines=[];
            
            for(var i=180;i<700;i++){
            	
            		var points=circle.rings[0][i];
            		lines.push(points);
            		
            	
            };
            
            
            
            var circle2 = new Circle({
                center: [longitude, latitude],
                radius: radius2,
                geodesic: true,
                numberOfPoints:1000,
                radiusUnit: "esriKilometers"
            });
            
            var lines2=[];
            for(var i=140;i<700;i++){
            	var points=circle2.rings[0][i];
            	lines2.push(points);
            };
            
            var line1=new Polyline(lines);
            var g1=new Graphic(line1, linsym);
            gLayer.add(g1);
            
            var line2=new Polyline(lines2);
            var g2=new Graphic(line2, linsym);
            gLayer.add(g2);
            
            
             var textSymbol1 = new TextSymbol("400公里").setColor(
                new Color(color)).setAlign(Font.ALIGN_START).setFont(
                new Font("12pt"));
                
            var textSymbol2 = new TextSymbol("800公里").setColor(
                new Color(color)).setAlign(Font.ALIGN_START).setFont(
                new Font("12pt"));
                
            var pt1=new Point(lines[lines.length-1][0],lines[lines.length-1][1]);
            var gtext1=new Graphic(pt1, textSymbol1);
            gLayer.add(gtext1);
            
            var pt2=new Point(lines2[lines2.length-1][0],lines2[lines2.length-1][1]);
            var gtext2=new Graphic(pt2, textSymbol2);
            gLayer.add(gtext2);
            
             var textSymbol3 = new TextSymbol("400公里").setColor(
                new Color(color)).setAlign(Font.ALIGN_START).setFont(
                new Font("12pt"));
            var pt3=new Point(lines[0][0],lines[0][1]);
            var gtext3=new Graphic(pt3, textSymbol3);
            gLayer.add(gtext3);
            
            var textSymbol4 = new TextSymbol("800公里").setColor(
                new Color(color)).setAlign(Font.ALIGN_START).setFont(
                new Font("12pt"));
            var pt4=new Point(lines2[0][0],lines2[0][1]);
            var gtext4=new Graphic(pt4, textSymbol4);
            gLayer.add(gtext4);  
                
            //var gtext2=new Graphic(line2, textSymbol2);
            //gLayer.add(gtext2);
/*            var circleGraphic1 = new Graphic(circle, circleSymb);
            //gLayer.add(circleGraphic1);
            
            var circleGraphic2 = new Graphic(circle2, circleSymb);
            gLayer.add(circleGraphic2);*/
            
            this.map.addLayer(gLayer);
        });
}

Mark.prototype.mark_circle = function (longitude, latitude, rad) {
    require([
        "esri/graphic",
        "esri/layers/GraphicsLayer",
        "esri/geometry/Polyline",
        "esri/geometry/Circle",
        "esri/symbols/SimpleLineSymbol",
        "esri/symbols/SimpleFillSymbol",
        "esri/Color"], function (
        Graphic, GraphicsLayer, Polyline, Circle, SimpleLineSymbol, SimpleFillSymbol, Color
        ) {
            var layer = new GraphicsLayer({ id: "circleLayer" });
            var circleSymb = new SimpleFillSymbol(
          SimpleFillSymbol.STYLE_SOLID,
          new SimpleLineSymbol(
            SimpleLineSymbol.STYLE_NULL,
            new Color([105, 105, 105]),
            1
          ), new Color([243, 146, 11, 0.5])
        );

            var circle = new Circle({
                center: [longitude, latitude],
                radius: rad,
                geodesic: true,
                radiusUnit: "esriKilometers"
            });
            var circleGraphic = new Graphic(circle, circleSymb);
            layer.add(circleGraphic);
            this.map.addLayer(layer, 20);
        });
}

Mark.prototype.Point_Text = function (text, x, y, color, size,offx,offy, layername) {
    require([
        "esri/geometry/Point",
        "esri/graphic",
        "esri/symbols/TextSymbol",
        "esri/Color", "esri/symbols/Font"], function (Point, Graphic, TextSymbol, Color, Font) {
            var gLayer;
            if (layername == "") {
                gLayer = this.map.graphics;
            } else {
                gLayer = this.map.getLayer(layername);
                if (gLayer == null) {
                    gLayer = new GraphicsLayer({ id: layername });
                    this.map.addLayer(gLayer);
                }
            }

            var textSymbol = new TextSymbol(text).setColor(
                new Color(color)).setAlign(Font.ALIGN_START).setFont(
                new Font(size).setWeight(Font.WEIGHT_BOLD)).setOffset(offx,offy);

            var point = new Point(x, y);
            var textGraphic = new Graphic(point, textSymbol);
            gLayer.add(textGraphic);
        });
}

Mark.prototype.Point_Text2 = function ( x, y,color, size,text, layername) {
    require([
        "esri/geometry/Point",
        "esri/graphic",
        "esri/symbols/SimpleMarkerSymbol",
        "esri/symbols/TextSymbol",
        "esri/Color", "esri/symbols/Font"], function (Point, Graphic, SimpleMarkerSymbol,TextSymbol, Color, Font) {
            var gLayer;
            if (layername == "") {
                gLayer = this.map.graphics;
            } else {
                gLayer = this.map.getLayer(layername);
                if (gLayer == null) {
                    gLayer = new GraphicsLayer({ id: layername });
                    this.map.addLayer(gLayer);
                }
            }
			
			var point = new Point(x, y);
			var sms = new SimpleMarkerSymbol().setStyle(
            SimpleMarkerSymbol.STYLE_CIRCLE).setColor(
            new Color(color)).setSize(10).setOutline(null);
			var graphic = new Graphic(point, sms);
            gLayer.add(graphic);
            var textSymbol = new TextSymbol(text).setColor(
                new Color(color)).setAlign(Font.ALIGN_START).setFont(
                new Font(size).setWeight(Font.WEIGHT_BOLD)).setOffset(30,-6);
                //textSymbol.setHaloColor([0,0,0]);
            var textGraphic = new Graphic(point, textSymbol);
            gLayer.add(textGraphic);
        });
}

Mark.prototype.mark_polygon = function (map, coordinates, text) {
    require([
        "esri/graphic",
        "esri/layers/GraphicsLayer",
        "esri/geometry/Polygon",
        "esri/symbols/SimpleFillSymbol",
        "esri/symbols/SimpleLineSymbol",
        "esri/symbols/TextSymbol",
        "esri/Color", "esri/symbols/Font"], function (
        Graphic, GraphicsLayer, Polygon,
        SimpleFillSymbol, SimpleLineSymbol, TextSymbol, Color, Font
        ) {
            var singleRingPolygon = new Polygon(coordinates);
            var symbol = new SimpleFillSymbol(SimpleFillSymbol.STYLE_SOLID,
                new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID,
                    new Color([147, 68, 77, 0.7]), 3), new Color([175, 175, 175, 0.35]));
            var graphic = new Graphic(singleRingPolygon, symbol);
            var graphicLayer = new GraphicsLayer();
            graphicLayer.add(graphic);
            var textSymbol = new TextSymbol(text).setColor(
                new Color([128, 0, 0])).setAlign(Font.ALIGN_START).setFont(
                new Font("10pt").setWeight(Font.WEIGHT_BOLD));

            var point = graphic.geometry.getCentroid();
            var textGraphic = new Graphic(point, textSymbol);
            graphicLayer.add(textGraphic);
            this.map.addLayer(graphicLayer);
            //graphicLayer.minScale = miniscale;
            //locate
            var extent = graphic.geometry.getExtent();
            this.fullExtent = extent;
            //this.map.setExtent(extent);  //定义地图范围
        });
}

Mark.prototype.mark_polygonwithcolor = function (map, coordinates, text, color) {
    require([
        "esri/graphic",
        "esri/layers/GraphicsLayer",
        "esri/geometry/Polygon",
        "esri/symbols/SimpleFillSymbol",
        "esri/symbols/SimpleLineSymbol",
        "esri/symbols/TextSymbol",
        "esri/Color", "esri/symbols/Font"], function (
        Graphic, GraphicsLayer, Polygon,
        SimpleFillSymbol, SimpleLineSymbol, TextSymbol, Color, Font
        ) {
            var layername = "WarnLayer";
            var gLayer;
            var layer = this.map.getLayer(layername);
            if (layer != null) {
                gLayer = layer;
            } else {
                gLayer = new GraphicsLayer({ id: layername, opacity: 0.5 });
                this.map.addLayer(gLayer);
            }
            //var graphicLayer = new GraphicsLayer({ opacity:0.5});
            var singleRingPolygon = new Polygon(coordinates);
            var symbol = new SimpleFillSymbol(SimpleFillSymbol.STYLE_SOLID,
                new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID,
                    new Color([147, 68, 77, 0.7]), 3), new Color(color));
            var attr = { "name": text };
            var graphic = new Graphic(singleRingPolygon, symbol, attr);
            gLayer.add(graphic);

            //var textSymbol = new TextSymbol(text).setColor(
            //    new Color([128, 0, 0])).setAlign(Font.ALIGN_START).setFont(
            //    new Font("10pt").setWeight(Font.WEIGHT_BOLD));

            //var point = graphic.geometry.getCentroid();
            //var textGraphic = new Graphic(point, textSymbol);
            //gLayer.add(textGraphic);

            //graphicLayer.minScale = miniscale;
            //locate
            var extent = graphic.geometry.getExtent();
            this.fullExtent = extent;
            //this.map.setExtent(extent);  //定义地图范围
        });
}


function ShowGraphicWithPop(x, y, title, content) {
    require(["esri/map", "esri/geometry/Point", "esri/symbols/Font", "esri/Color", "dijit/TooltipDialog", "esri/lang", "dijit/popup", "dojo/number", "dojo/dom-style", "dojo/domReady!"],
        function (Map, Point, Font, Color, TooltipDialog, esriLang, dijitPopup, number, domStyle) {
        var mpt = new Point(x, y);
        var screenpt = this.map.toScreen(mpt);
        //this.map.infoWindow.setTitle(title);
        this.map.infoWindow.setContent(content);
        this.map.infoWindow.show(screenpt, this.map.getInfoWindowAnchor(screenpt));

        //var textDiv = dojo.doc.createElement("div");
        //dojo.attr(textDiv, {
        //    "id": "text"
        //});
        //dojo.style(textDiv, {
        //    "left": screenpt.x - 10 + "px",
        //    "top": screenpt.y - 30 + "px",
        //    "position": "absolute",
        //    "z-index": 99,
        //    "background": "#fcffd1",
        //    "font-size": "10px",
        //    "border": "1px solid #0096ff",
        //    "padding": "0.1em 0.3em 0.1em",
        //    "font-size": "11px",
        //    "border-radius": "3px",
        //    "box-shadow": "0 0 0.75em #777777"
        //});
        //textDiv.innerHTML = '<div>' + content + '</div>';
        //dojo.byId("map").appendChild(textDiv);
    });
}

//设置地图的中心点和zoom
Mark.prototype.setMapView=function(x,y,zoom){
	require(["esri/geometry/Point","esri/SpatialReference"],function(Point,SpatialReference){
		var mapcenter=new Point(x,y,new SpatialReference(4326));
		this.map.centerAndZoom(mapcenter,zoom);
	});
}