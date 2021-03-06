import '../css/core/navbar.css'
import '../css/core/core.css'
import GalleryWaterfall from './user/public/gallery/GalleryWaterfall.vue'
import ActivityWallView from '@/user/ui/activity/ActivityWallView.vue'
window.easejs = require('easejs')
window.axios = require('axios')

try {
    Vue.component('gallery-waterfall-component', GalleryWaterfall);
    Vue.component('activity-wall', ActivityWallView);

    document.addEventListener("DOMContentLoaded",() => {
        document.querySelectorAll("[vue-component='init']").forEach(e => new Vue({el:e}) );
    });
} catch(e) {
    console.error(e);
}