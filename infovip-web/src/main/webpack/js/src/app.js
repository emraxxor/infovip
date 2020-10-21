import '../css/core/navbar.css'
import '../css/core/core.css'
import GalleryWaterfall from './user/public/gallery/GalleryWaterfall.vue'
window._ = require('lodash');
window.easejs = require('easejs')

try {
    Vue.component('gallery-waterfall-component', GalleryWaterfall);


    document.addEventListener("DOMContentLoaded",() => {
        document.querySelectorAll("[vue-component='init']").forEach(e => new Vue({el:e}) );
    });
} catch(e) {
    console.error(e);
}