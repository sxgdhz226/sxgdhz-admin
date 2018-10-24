var commonConfig = {
	/**
	 * 图层名称
	 */
	baseLayerName: 'ssd:china',
	/**
	 * 数据投影
	 */
	dataProjection: 'EPSG:4326',
	/**
	 * 要素投影
	 */
	featureProjection: 'EPSG:3857',

	/**
	 * 地图初始化中心点，zoom初始化缩放等级，minzoom最小缩放等级，maxzoom 最小缩放等级
	 */
	baseLayerCenter: [107.5795,22.2788],
	baseLayerZoom: 4,
	baseLayerMinZoom: 5,
	baseLayerMaxZoom: 8,
}

liveconfig = {
	/**所有的请求方式**/
	ajaxDataType:'jsonp',
	/**得到台风路径**/
//	getTyphoonRoute:'data/typhoon.json',
	getTyphoonRoute:'/web/tfljIntersk',
	/**得到台风预报信息**/
	getTyphoonForcast:'/web/tfljInteryb',
	/**台风列表**/
	getTyphoonList:'/web/tfljcallback',
//	getTyphoonList:'http://www.zhmb.gov.cn:8080/jeecms/web/tflj',
	/**获得首页的最新台风列表**/
	getNewTyphoonLiat:'/web/typhoonindex',
	/**
	 * 实况中英文对应字典
	 */
	livedictionary: {

		"1小时降水": "RainAmountHour",
		"能见度": "visib",
		"降水量": "rainamount",
		"温度": "temp",
		"3小时降雨": "pre_3h",
		"6小时降雨": "pre_6h",
		"12小时降雨": "pre_12h",
		"24小时降雨": "pre_24h",
		"1小时极大风向风速": "ExMaxWindDV",
		"风力": "windv",
		"1小时最大风向风速": "MaxWindDV",
		"日最大风速": "MaxWindVDay",
		"气压": "press",
		"相对湿度": "relhum",
		"3小时降水":"03",
		"6小时降水":"06",
		"9小时降水":"09",
		"12小时降水":"12",
		"24小时降水":"24",
		"48小时降水":"48",
		"08时~08时降水":"0808",
		"08时~20时降水":"0820",
		"20时~20时降水":"2020",
		"20时~08时降水":"2008",
	},
}
