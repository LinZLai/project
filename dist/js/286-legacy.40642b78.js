"use strict";(self["webpackChunksystem_vue"]=self["webpackChunksystem_vue"]||[]).push([[286],{11286:function(e,t,r){r.r(t),r.d(t,{default:function(){return l}});var s=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"login"},[r("div",{staticStyle:{margin:"200px auto","background-color":"#fff",width:"350px",height:"300px",padding:"20px","border-radius":"10px"}},[e._m(0),r("el-form",{ref:"userForm",attrs:{model:e.user,rules:e.rules}},[r("el-form-item",{attrs:{prop:"username"}},[r("el-input",{staticStyle:{margin:"10px 0",width:"90%"},attrs:{size:"medium",placeholder:"请输入账号","prefix-icon":"el-icon-user"},model:{value:e.user.username,callback:function(t){e.$set(e.user,"username",t)},expression:"user.username"}})],1),r("el-form-item",{attrs:{prop:"password"}},[r("el-input",{staticStyle:{margin:"10px 0",width:"90%"},attrs:{size:"medium",placeholder:"请输入密码","prefix-icon":"el-icon-lock","show-password":""},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.login.apply(null,arguments)}},model:{value:e.user.password,callback:function(t){e.$set(e.user,"password",t)},expression:"user.password"}})],1),r("el-form-item",{staticStyle:{margin:"20px auto","text-align":"center"}},[r("el-button",{attrs:{type:"warning",size:"medium",autocomplete:"off"},on:{click:function(t){return e.$router.push("/register")}}},[e._v("注册")]),r("el-button",{attrs:{type:"primary",size:"medium",autocomplete:"off"},on:{click:e.login}},[e._v("登录")])],1)],1)],1)])},n=[function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticStyle:{margin:"20px 0","text-align":"center","font-size":"24px"}},[r("b",[e._v("登 录")])])}],a={name:"Login",data:function(){return{user:{username:"admin",password:"12345678"},rules:{username:[{required:!0,message:"请输入用户名",trigger:"blur"},{min:3,max:10,message:"长度在 3 到 5 个字符",trigger:"blur"}],password:[{required:!0,message:"请输入密码",trigger:"blur"},{min:8,max:16,message:"长度在 8 到 16 个字符",trigger:"blur"}]}}},mounted:function(){},methods:{keydown:function(e){console.log(e.key),13===e.keyCode&&this.login()},login:function(){var e=this;this.$refs["userForm"].validate((function(t){t&&e.$axios.post("/login",e.user).then((function(t){if("200"==t.data.code){e.$message.success("登录成功");var r=t.headers["authorization"];e.$store.commit("SET_TOKEN",r),e.$store.commit("SET_USERINFO",t.data.data),e.$router.push({path:"/"+t.data.data.router})}else e.$message.error(t.data.msg)}))}))}}},i=a,o=r(1001),u=(0,o.Z)(i,s,n,!1,null,"496f04c8",null),l=u.exports}}]);
//# sourceMappingURL=286-legacy.40642b78.js.map