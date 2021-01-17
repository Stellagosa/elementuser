import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../components/Home.vue'
import UserTable from "../views/UserTable";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: {
      name: 'Home'
    }
  },
  {
    path: '/home',
    name: 'Home',
    component: Home,
    meta: {title: '首页'},
    children: [
      {
        path: '/home/userTable',
        name: 'UserTable',
        component: UserTable,
        meta: {title: '用户管理'}
      }
    ]
  }
]

const router = new VueRouter({
  routes
})

export default router
