"use strict";(self["webpackChunksystem_vue"]=self["webpackChunksystem_vue"]||[]).push([[560],{7560:function(t,e,a){a.r(e),a.d(e,{default:function(){return d}});var i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("highcharts",{staticClass:"charts-content",attrs:{options:t.optionConfig,callback:t.myCallback}}),a("p",[t._v("近三年收入")]),a("ul",[a("li",[a("span",[a("h1",[t._v("今年总收入")]),t._v(" ￥："+t._s(this.allIncome.now))])]),a("li",[a("span",[a("h1",[t._v("去年总收入")]),t._v(" ￥："+t._s(this.allIncome.last))])]),a("li",[a("span",[a("h1",[t._v("前年总收入")]),t._v(" ￥："+t._s(this.allIncome.before))])]),a("li",[t._m(0),a("div",{staticClass:"echart",style:t.myChartStyle,attrs:{id:"mychart"}})])])],1)},n=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("h1",{staticStyle:{"line-height":"0px"}},[t._v(" 三年对比"),a("span",{staticStyle:{"font-size":"10px","font-weight":"400",color:"#999"}},[t._v("（单位:K）")])])}],s=a(8522);const l={title:{text:"2022年收入统计图"},subtitle:{text:"数据来源：MySQL"},xAxis:{categories:["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"]},yAxis:{title:{text:"收入详情"}},legend:{layout:"vertical",align:"right",verticalAlign:"middle"},plotOptions:{series:{label:{connectorAllowed:!1}}},series:[{name:"收入曲线",data:[43934,52503,57177,69658,97031,119931,137133,154175,97031,119931,137133,154175]}],responsive:{rules:[{condition:{maxWidth:500},chartOptions:{legend:{layout:"horizontal",align:"center",verticalAlign:"bottom"}}}]}};var o={name:"Finance",data(){return{newYear:"",optionConfig:{},allIncome:{now:1e4,last:2e4,before:3e4},xData:["今年","去年","前年"],yData:[],myChartStyle:{float:"right",width:"105%",height:"190px",margin:"-40px -15px 0 15px"}}},created(){this.optionConfig=l},updated(){},mounted(){this.initEcharts()},watch:{},methods:{myCallback(){this.$axios.get("/admin/getIncome").then((t=>{this.optionConfig.series[0].data=t.data.data.income,this.newYear=t.data.data.year,this.allIncome.now=t.data.data.incomeEveryYear[0],this.allIncome.last=t.data.data.incomeEveryYear[1],this.allIncome.before=t.data.data.incomeEveryYear[2],this.yData=t.data.data.incomeEveryYear,this.initEcharts(),this.optionConfig.title.text=`${this.newYear}年收入统计图`}))},initEcharts(){const t={xAxis:{data:this.xData},yAxis:{title:{text:"单位:K"}},series:[{type:"bar",data:this.yData}]},e=s.init(document.getElementById("mychart"));e.setOption(t),window.addEventListener("resize",(()=>{e.resize()}))}}},r=o,c=a(1001),h=(0,c.Z)(r,i,n,!1,null,"142faf93",null),d=h.exports}}]);
//# sourceMappingURL=560.0dc248d0.js.map