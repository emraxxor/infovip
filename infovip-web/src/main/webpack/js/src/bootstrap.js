//import 'bootstrap/dist/css/bootstrap.min.css';
import '../css/bootstrap_custom/bootstrap.min.css';
import '../css/bootstrap_custom/bootstrap-theme.min.css';

import 'jquery-ui/themes/base/theme.css';
import 'jquery-ui/ui/core';
import 'jquery-ui/ui/widgets/datepicker';
import 'jquery-ui/ui/widgets/mouse';

window._ = require('lodash');

try {
    
    window.Popper = require('popper.js').default;
    window.jQuery = require('jquery'),  require('jquery-ui');
} catch (e) {
    console.error(e);
}

try {
    require('bootstrap');
} catch (e) {
    console.error(e);
}