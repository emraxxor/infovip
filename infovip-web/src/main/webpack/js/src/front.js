
import '../css/core/main.css'
import '../css/template/social/template.css'
import '../css/template/social/style.css'
import '../css/template/social/responsive.css'
import '../css/core/profile.css'
import '../css/core/home.css'

try {
    window.Mustache = require('mustache')
    window.Handlebars = require('handlebars')
    window.moment = require('moment')
} catch(e) {
    console.error(e)
}