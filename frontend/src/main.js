// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import './plugins/vuetify'
import App from './App'
import router from './router'

Vue.config.productionTip = false

Vue.prototype.$API_URL = 'http://localhost:9095/ESC'
// Vue.prototype.$API_URL = 'http://119.207.76.92:9095/ESC'

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  iconfont: 'mdi',
  template: '<App/>',
  components: { App }
})
