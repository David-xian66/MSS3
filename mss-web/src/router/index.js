import {createRouter, createWebHashHistory} from 'vue-router'
import Login from '../views/Login.vue'
import Home from "../views/Home";

const routes = [
    {
        path : '/login',
        component: Login,
        meta : {
            title : "MSS3-登录",
        }
    },
    {
        path : '/',
        component: Home,
        meta : {
            title : "MSS3-主页",
        }
    }
]

const router = createRouter({
    routes,
    history : createWebHashHistory("public"),
});

export default router;

