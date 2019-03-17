import Vue from 'vue'
import Router from 'vue-router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import Vuetify from 'vuetify'
import TreeView from "vue-json-tree-view"
import 'material-design-icons-iconfont/dist/material-design-icons.css' 
import HighchartsVue from 'highcharts-vue'
import Hello from '@/components/Hello'
import Test from '@/components/Test'
import Timeline from '@/components/Timeline'
import BCA from '@/components/BCA.vue'

Vue.use(Router)
Vue.use(VueAxios, axios)
Vue.use(Vuetify, {
  iconfont: 'mdi' // 'md' || 'mdi' || 'fa' || 'fa4'
})
Vue.use(TreeView)
Vue.use(HighchartsVue)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'BCA',
      component:BCA
    },
    {
      path: '/timeline',
      name: 'Timeline',
      component: Timeline
    },
    {
      path: '/test',
      name: 'Test',
      component:Test
    },
    { path: '*', component: { template: '<div>Not Found</div>'} }
  ]
})
