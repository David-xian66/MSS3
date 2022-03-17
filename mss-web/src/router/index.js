import {createRouter, createWebHashHistory} from 'vue-router'
import Login from '../views/Login.vue'
import Home from "../views/Home";
import ServerManager from "../views/ServerManager";
import ServerInput from "../views/ServerInput";
import ServerSetting from "../views/ServerSetting";

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
    },
    {
        path : '/serverManager',
        component: ServerManager,
        meta : {
            title : "MSS3-服务端管理",
        }
    },
    {
        path : '/serverInput',
        component: ServerInput,
        meta : {
            title : "MSS3-服务端导入",
        }
    },
    {
        path : '/serverSetting',
        component: ServerSetting,
        meta : {
            title : "MSS3-服务端设置",
        }
    }
]

const router = createRouter({
    routes,
    history : createWebHashHistory("public"),
});

export default router;

