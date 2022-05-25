"use strict";(self["webpackChunksystem_vue"]=self["webpackChunksystem_vue"]||[]).push([[560],{37560:function(t,e,a){a.r(e),a.d(e,{default:function(){return d}});var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("highcharts",{staticClass:"charts-content",attrs:{options:t.optionConfig,callback:t.myCallback}}),a("p",[t._v("近三年收入")]),a("ul",[a("li",[a("span",[a("h1",[t._v("今年总收入")]),t._v(" ￥："+t._s(this.allIncome.now))])]),a("li",[a("span",[a("h1",[t._v("去年总收入")]),t._v(" ￥："+t._s(this.allIncome.last))])]),a("li",[a("span",[a("h1",[t._v("前年总收入")]),t._v(" ￥："+t._s(this.allIncome.before))])]),a("li",[t._m(0),a("div",{staticClass:"echart",style:t.myChartStyle,attrs:{id:"mychart"}})])])],1)},i=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("h1",{staticStyle:{"line-height":"0px"}},[t._v(" 三年对比"),a("span",{staticStyle:{"font-size":"10px","font-weight":"400",color:"#999"}},[t._v("（单位:K）")])])}],o=a(8522),s={title:{text:"2022年收入统计图"},subtitle:{text:"数据来源：MySQL"},xAxis:{categories:["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"]},yAxis:{title:{text:"收入详情"}},legend:{layout:"vertical",align:"right",verticalAlign:"middle"},plotOptions:{series:{label:{connectorAllowed:!1}}},series:[{name:"收入曲线",data:[43934,52503,57177,69658,97031,119931,137133,154175,97031,119931,137133,154175]}],responsive:{rules:[{condition:{maxWidth:500},chartOptions:{legend:{layout:"horizontal",align:"center",verticalAlign:"bottom"}}}]}},l={name:"Finance",data:function(){return{newYear:"",optionConfig:{},allIncome:{now:1e4,last:2e4,before:3e4},xData:["今年","去年","前年"],yData:[],myChartStyle:{float:"right",width:"105%",height:"190px",margin:"-40px -15px 0 15px"}}},created:function(){this.optionConfig=s},updated:function(){},mounted:function(){this.initEcharts()},watch:{},methods:{myCallback:function(){var t=this;this.$axios.get("/admin/getIncome").then((function(e){t.optionConfig.series[0].data=e.data.data.income,t.newYear=e.data.data.year,t.allIncome.now=e.data.data.incomeEveryYear[0],t.allIncome.last=e.data.data.incomeEveryYear[1],t.allIncome.before=e.data.data.incomeEveryYear[2],t.yData=e.data.data.incomeEveryYear,t.initEcharts(),t.optionConfig.title.text="".concat(t.newYear,"年收入统计图")}))},initEcharts:function(){var t={xAxis:{data:this.xData},yAxis:{title:{text:"单位:K"}},series:[{type:"bar",data:this.yData}]},e=o.init(document.getElementById("mychart"));e.setOption(t),window.addEventListener("resize",(function(){e.resize()}))}}},r=l,c=a(1001),h=(0,c.Z)(r,n,i,!1,null,"142faf93",null),d=h.exports}}]);
//# sourceMappingURL=560-legacy.3518870d.js.map