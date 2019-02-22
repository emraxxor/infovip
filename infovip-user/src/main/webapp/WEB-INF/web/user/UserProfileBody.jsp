<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<c:if test="${not empty isAuthenticated and isAuthenticated == true }">


<tiles:insertTemplate template="${Configuration.WEB_DIRECTORY}/user/header/header.jsp"></tiles:insertTemplate>  			
  
 <section class="page--wrapper pt--80 pb--20" style="transform: none;">
            <div class="container" style="transform: none;">
                <div class="row" style="transform: none;">
                    <!-- Main Content Start -->
                    <div class="main--content col-md-8 pb--60" data-trigger="stickyScroll" style="position: relative; overflow: visible; box-sizing: border-box; min-height: 1px;">
                        
                    <div class="theiaStickySidebar" style="padding-top: 0px; padding-bottom: 1px; position: relative; transform: none;"><div class="main--content-inner drop--shadow">
                            <!-- Content Nav Start -->
                            <div class="content--nav pb--30">
                                <ul class="nav ff--primary fs--14 fw--500 bg-lighter">
                                    <li><a href="member-activity-personal.html">Activity</a></li>
                                    <li class="active"><a href="member-profile.html">Profile</a></li>
                                    <li><a href="member-friends.html">Friends</a></li>
                                    <li><a href="member-groups.html">Groups</a></li>
                                    <li><a href="member-forum-topics.html">Forum</a></li>
                                    <li><a href="member-media-all.html">Media</a></li>
                                </ul>
                            </div>
                            <!-- Content Nav End -->

                            <!-- Profile Details Start -->
                            <div class="profile--details fs--14">
                                <!-- Profile Item Start -->
                                <div class="profile--item">
                                    <div class="profile--heading">
                                        <h3 class="h4 fw--700">
                                            <span class="mr--4">About Me</span>
                                            <i class="ml--10 text-primary fa fa-caret-right"></i>
                                        </h3>
                                    </div>

                                    <div class="profile--info">
                                        <table class="table">
                                            <tbody><tr>
                                                <th class="fw--700 text-darkest">Full Name</th>
                                                <td><a href="#" class="btn-link">Eileen K. Ruiz</a></td>
                                            </tr>
                                            <tr>
                                                <th class="fw--700 text-darkest">Skill</th>
                                                <td>Graphic Design, Font-End Development, Web Development</td>
                                            </tr>
                                            <tr>
                                                <th class="fw--700 text-darkest">Date of Birth</th>
                                                <td>19<sup>th</sup> January 2017</td>
                                            </tr>
                                        </tbody></table>
                                    </div>
                                </div>
                                <!-- Profile Item End -->

                                <!-- Profile Item Start -->
                                <div class="profile--item">
                                    <div class="profile--heading">
                                        <h3 class="h4 fw--700">
                                            <span class="mr--4">Biography</span>
                                            <i class="ml--10 text-primary fa fa-caret-right"></i>
                                        </h3>
                                    </div>

                                    <div class="profile--info">
                                        <p>Hello ! I’m <a href="#">Eileen K. Ruiz</a>. Senior web developer of themelooks.com from last 5 years many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing</p>
                                    </div>
                                </div>
                                <!-- Profile Item End -->

                                <!-- Profile Item Start -->
                                <div class="profile--item">
                                    <div class="profile--heading">
                                        <h3 class="h4 fw--700">
                                            <span class="mr--4">Work Experience</span>
                                            <i class="ml--10 text-primary fa fa-caret-right"></i>
                                        </h3>
                                    </div>

                                    <div class="profile--info">
                                        <dl>
                                            <dt>
                                                <p class="h6 fw--700 text-darkest">Graphic Designer 2010 - 2012</p>
                                                <p><small class="fw--400 fs--12 text-darker">Graphicriver.net at Sydney</small></p>
                                            </dt>
                                            <dd>
                                                <p>There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour which don't look even slightly believable.</p>
                                            </dd>

                                            <dt>
                                                <p class="h6 fw--700 text-darkest">Font-End Developer 2012 - 2014</p>
                                                <p><small class="fw--400 fs--12 text-darker">Themeforest.net at Australia</small></p>
                                            </dt>
                                            <dd>
                                                <p>There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour which don't look even slightly believable.</p>
                                            </dd>

                                            <dt>
                                                <p class="h6 fw--700 text-darkest">Web Developer 2014 - Still Now</p>
                                                <p><small class="fw--400 fs--12 text-darker">Codecanyon.net at Sydney</small></p>
                                            </dt>
                                            <dd>
                                                <p>There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour which don't look even slightly believable.</p>
                                            </dd>
                                        </dl>
                                    </div>
                                </div>
                                <!-- Profile Item End -->

                                <!-- Profile Item Start -->
                                <div class="profile--item">
                                    <div class="profile--heading">
                                        <h3 class="h4 fw--700">
                                            <span class="mr--4">Contact</span>
                                            <i class="ml--10 text-primary fa fa-caret-right"></i>
                                        </h3>
                                    </div>

                                    <div class="profile--info">
                                        <table class="table">
                                            <tbody><tr>
                                                <th class="fw--700 text-darkest">Phone</th>
                                                <td><a href="tel:+16105598246">+1610-559-8246</a></td>
                                            </tr>
                                            <tr>
                                                <th class="fw--700 text-darkest">E-mail</th>
                                                <td><a href="mailto:demo@fakemail.com">demo@example.com</a></td>
                                            </tr>
                                            <tr>
                                                <th class="fw--700 text-darkest">Website</th>
                                                <td><a href="#">example.com</a></td>
                                            </tr>
                                            <tr>
                                                <th class="fw--700 text-darkest">Address</th>
                                                <td>123 Lorem St., California, United States</td>
                                            </tr>
                                        </tbody></table>
                                    </div>
                                </div>
                                <!-- Profile Item End -->
                            </div>
                            <!-- Profile Details End -->
                        </div><div class="resize-sensor" style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; z-index: -1; visibility: hidden;"><div class="resize-sensor-expand" style="position: absolute; left: 0; top: 0; right: 0; bottom: 0; overflow: hidden; z-index: -1; visibility: hidden;"><div style="position: absolute; left: 0px; top: 0px; transition: all 0s ease 0s; width: 760px; height: 1039px;"></div></div><div class="resize-sensor-shrink" style="position: absolute; left: 0; top: 0; right: 0; bottom: 0; overflow: hidden; z-index: -1; visibility: hidden;"><div style="position: absolute; left: 0; top: 0; transition: 0s; width: 200%; height: 200%"></div></div></div></div></div>
                    <!-- Main Content End -->

                    <!-- Main Sidebar Start -->
                    <div class="main--sidebar col-md-4 pb--60" data-trigger="stickyScroll" style="position: relative; overflow: visible; box-sizing: border-box; min-height: 1px;">
                        <!-- Widget Start -->
                        
                        <!-- Widget End -->

                        <!-- Widget Start -->
                        
                        <!-- Widget End -->

                        <!-- Widget Start -->
                        
                        <!-- Widget End -->

                        <!-- Widget Start -->
                        
                        <!-- Widget End -->

                        <!-- Widget Start -->
                        
                        <!-- Widget End -->
                    <div class="theiaStickySidebar" style="padding-top: 0px; padding-bottom: 1px; position: relative; transform: none;"><div class="widget">
                            <h2 class="h4 fw--700 widget--title">Find A Buddy</h2>

                            <!-- Buddy Finder Widget Start -->
                            <div class="buddy-finder--widget">
                                <form action="#">
                                    <div class="row">
                                        <div class="col-xs-6 col-xxs-12">
                                            <div class="form-group">
                                                <label>
                                                    <span class="text-darker ff--primary fw--500">I Am</span>

                                                    <select name="gender" class="form-control form-sm" data-trigger="selectmenu" style="display: none;">
                                                        <option value="male">Male</option>
                                                        <option value="female">Female</option>
                                                        <option value="other">Other</option>
                                                    </select><div class="custom-select-menu" tabindex="0"><span class="selection-made">Male</span><ul data-select-name="gender" style="display: none;"><li data-option-value="male" class="selected">Male</li><li data-option-value="female">Female</li><li data-option-value="other">Other</li></ul><input type="hidden" name="gender" value="male"></div>
                                                </label>
                                            </div>
                                        </div>

                                        <div class="col-xs-6 col-xxs-12">
                                            <div class="form-group">
                                                <label>
                                                    <span class="text-darker ff--primary fw--500">Looking For</span>

                                                    <select name="lookingfor" class="form-control form-sm" data-trigger="selectmenu" style="display: none;">
                                                        <option value="female">Female</option>
                                                        <option value="male">Male</option>
                                                        <option value="other">Other</option>
                                                    </select><div class="custom-select-menu" tabindex="0"><span class="selection-made">Female</span><ul data-select-name="lookingfor" style="display: none;"><li data-option-value="female" class="selected">Female</li><li data-option-value="male">Male</li><li data-option-value="other">Other</li></ul><input type="hidden" name="lookingfor" value="female"></div>
                                                </label>
                                            </div>
                                        </div>

                                        <div class="col-xs-6 col-xxs-12">
                                            <div class="form-group">
                                                <label>
                                                    <span class="text-darker ff--primary fw--500">Age</span>

                                                    <select name="age" class="form-control form-sm" data-trigger="selectmenu" style="display: none;">
                                                        <option value="18to25">18 to 25</option>
                                                        <option value="25to30">25 to 30</option>
                                                        <option value="30to35">30 to 35</option>
                                                        <option value="35to40">35 to 40</option>
                                                        <option value="40plus">40+</option>
                                                    </select><div class="custom-select-menu" tabindex="0"><span class="selection-made">18 to 25</span><ul data-select-name="age" style="display: none;"><li data-option-value="18to25" class="selected">18 to 25</li><li data-option-value="25to30">25 to 30</li><li data-option-value="30to35">30 to 35</li><li data-option-value="35to40">35 to 40</li><li data-option-value="40plus">40+</li></ul><input type="hidden" name="age" value="18to25"></div>
                                                </label>
                                            </div>
                                        </div>

                                        <div class="col-xs-6 col-xxs-12">
                                            <div class="form-group">
                                                <label>
                                                    <span class="text-darker ff--primary fw--500">City</span>

                                                    <select name="city" class="form-control form-sm" data-trigger="selectmenu" style="display: none;">
                                                        <option value="newyork">New York</option>
                                                        <option value="California">California</option>
                                                        <option value="Atlanta">Atlanta</option>
                                                    </select><div class="custom-select-menu" tabindex="0"><span class="selection-made">New York</span><ul data-select-name="city" style="display: none;"><li data-option-value="newyork" class="selected">New York</li><li data-option-value="California">California</li><li data-option-value="Atlanta">Atlanta</li></ul><input type="hidden" name="city" value="newyork"></div>
                                                </label>
                                            </div>
                                        </div>

                                        <div class="col-xs-12">
                                            <div class="form-group">
                                                <label>
                                                    <span class="text-darker ff--primary fw--500">Filter Country</span>

                                                    <select name="city" class="form-control form-sm" data-trigger="selectmenu" style="display: none;">
                                                        <option value="unitedstates">United States</option>
                                                        <option value="australia">Australia</option>
                                                        <option value="turkey">Turkey</option>
                                                        <option value="vietnam">Vietnam</option>
                                                    </select><div class="custom-select-menu" tabindex="0"><span class="selection-made">United States</span><ul data-select-name="city" style="display: none;"><li data-option-value="unitedstates" class="selected">United States</li><li data-option-value="australia">Australia</li><li data-option-value="turkey">Turkey</li><li data-option-value="vietnam">Vietnam</li></ul><input type="hidden" name="city" value="unitedstates"></div>
                                                </label>
                                            </div>
                                        </div>

                                        <div class="col-xs-12">
                                            <button type="submit" class="btn btn-primary">Search</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <!-- Buddy Finder Widget End -->
                        </div><div class="widget">
                            <h2 class="h4 fw--700 widget--title">Notice</h2>

                            <!-- Text Widget Start -->
                            <div class="text--widget">
                                <p>There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some  look even slightly believable.</p>
                            </div>
                            <!-- Text Widget End -->
                        </div><div class="widget">
                            <h2 class="h4 fw--700 widget--title">Forums</h2>

                            <!-- Links Widget Start -->
                            <div class="links--widget">
                                <ul class="nav">
                                    <li><a href="sub-forums.html">User Interface Design<span>(12)</span></a></li>
                                    <li><a href="sub-forums.html">Front-End Engineering<span>(07)</span></a></li>
                                    <li><a href="sub-forums.html">Web Development<span>(37)</span></a></li>
                                    <li><a href="sub-forums.html">Social Media Marketing<span>(13)</span></a></li>
                                    <li><a href="sub-forums.html">Content Marketing<span>(28)</span></a></li>
                                </ul>
                            </div>
                            <!-- Links Widget End -->
                        </div><div class="widget">
                            <h2 class="h4 fw--700 widget--title">Archives</h2>

                            <!-- Nav Widget Start -->
                            <div class="nav--widget">
                                <ul class="nav">
                                    <li>
                                        <a href="#">
                                            <i class="fa fa-calendar-o"></i>
                                            <span class="text">Jan - July 2017</span>
                                            <span class="count">(86)</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="fa fa-calendar-o"></i>
                                            <span class="text">Jan - Dce 2016</span>
                                            <span class="count">(328)</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="fa fa-calendar-o"></i>
                                            <span class="text">Jan - Dec 2015</span>
                                            <span class="count">(427)</span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            <!-- Nav Widget End -->
                        </div><div class="widget">
                            <h2 class="h4 fw--700 widget--title">Advertisements</h2>

                            <!-- Ad Widget Start -->
                            <div class="ad--widget">
                                <a href="#">
                                    <img src="img/widgets-img/ad.jpg" alt="" class="center-block">
                                </a>
                            </div>
                            <!-- Ad Widget End -->
                        </div><div class="resize-sensor" style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; z-index: -1; visibility: hidden;"><div class="resize-sensor-expand" style="position: absolute; left: 0; top: 0; right: 0; bottom: 0; overflow: hidden; z-index: -1; visibility: hidden;"><div style="position: absolute; left: 0px; top: 0px; transition: all 0s ease 0s; width: 370px; height: 1300px;"></div></div><div class="resize-sensor-shrink" style="position: absolute; left: 0; top: 0; right: 0; bottom: 0; overflow: hidden; z-index: -1; visibility: hidden;"><div style="position: absolute; left: 0; top: 0; transition: 0s; width: 200%; height: 200%"></div></div></div></div></div>
                    <!-- Main Sidebar End -->
                </div>
            </div>
        </section>

</c:if>