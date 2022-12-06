
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import CatManager from "./components/listers/CatCards"
import CatDetail from "./components/listers/CatDetail"

import CatShipManager from "./components/listers/CatShipCards"
import CatShipDetail from "./components/listers/CatShipDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/cats',
                name: 'CatManager',
                component: CatManager
            },
            {
                path: '/cats/:id',
                name: 'CatDetail',
                component: CatDetail
            },

            {
                path: '/catShips',
                name: 'CatShipManager',
                component: CatShipManager
            },
            {
                path: '/catShips/:id',
                name: 'CatShipDetail',
                component: CatShipDetail
            },



    ]
})
