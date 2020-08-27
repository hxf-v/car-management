import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import CarTravel from './components/carTravel'
import CarInfo from './components/carInfo'
import CarStop from './components/carStop'
import CarRegister from './components/carRegister'
import Login from './views/Login'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      redirect: { name: 'login' }
    },
    {
      path: '/home',
      name: 'home',
      component: Home,
      redirect: { name: 'carTravel' },
      children: [
        {
          path: 'carTravel',
          name: 'carTravel',
          component: CarTravel
        },
        {
          path: 'carInfo',
          component: CarInfo
        },
        {
          path: 'carStop',
          component: CarStop
        },
        {
          path: 'carRegister',
          component: CarRegister
        }
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    }
  ]
})
