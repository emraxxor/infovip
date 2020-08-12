<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/tlds/module-manager" prefix="module"%>
<%@ taglib uri="/tlds/translate" prefix="tr"%>



	<div class="theiaStickySidebar"
		style="padding-top: 0px; padding-bottom: 1px; position: relative; transform: none;">
		<div class="widget">
			<h2 class="h4 fw--700 widget--title">Find A Buddy</h2>

			<!-- Buddy Finder Widget Start -->
			<div class="buddy-finder--widget">
				<form action="#">
					<div class="row">
						<div class="col-xs-6 col-xxs-12">
							<div class="form-group">
								<label> <span class="text-darker ff--primary fw--500">I
										Am</span> <select name="gender" class="form-control form-sm"
									data-trigger="selectmenu" style="display: none;">
										<option value="male">Male</option>
										<option value="female">Female</option>
										<option value="other">Other</option>
								</select>
									<div class="custom-select-menu" tabindex="0">
										<span class="selection-made">Male</span>
										<ul data-select-name="gender" style="display: none;">
											<li data-option-value="male" class="selected">Male</li>
											<li data-option-value="female">Female</li>
											<li data-option-value="other">Other</li>
										</ul>
										<input type="hidden" name="gender" value="male">
									</div>
								</label>
							</div>
						</div>

						<div class="col-xs-6 col-xxs-12">
							<div class="form-group">
								<label> <span class="text-darker ff--primary fw--500">Looking
										For</span> <select name="lookingfor" class="form-control form-sm"
									data-trigger="selectmenu" style="display: none;">
										<option value="female">Female</option>
										<option value="male">Male</option>
										<option value="other">Other</option>
								</select>
									<div class="custom-select-menu" tabindex="0">
										<span class="selection-made">Female</span>
										<ul data-select-name="lookingfor" style="display: none;">
											<li data-option-value="female" class="selected">Female</li>
											<li data-option-value="male">Male</li>
											<li data-option-value="other">Other</li>
										</ul>
										<input type="hidden" name="lookingfor" value="female">
									</div>
								</label>
							</div>
						</div>

						<div class="col-xs-6 col-xxs-12">
							<div class="form-group">
								<label> <span class="text-darker ff--primary fw--500">Age</span>

									<select name="age" class="form-control form-sm"
									data-trigger="selectmenu" style="display: none;">
										<option value="18to25">18 to 25</option>
										<option value="25to30">25 to 30</option>
										<option value="30to35">30 to 35</option>
										<option value="35to40">35 to 40</option>
										<option value="40plus">40+</option>
								</select>
									<div class="custom-select-menu" tabindex="0">
										<span class="selection-made">18 to 25</span>
										<ul data-select-name="age" style="display: none;">
											<li data-option-value="18to25" class="selected">18 to 25</li>
											<li data-option-value="25to30">25 to 30</li>
											<li data-option-value="30to35">30 to 35</li>
											<li data-option-value="35to40">35 to 40</li>
											<li data-option-value="40plus">40+</li>
										</ul>
										<input type="hidden" name="age" value="18to25">
									</div>
								</label>
							</div>
						</div>

						<div class="col-xs-6 col-xxs-12">
							<div class="form-group">
								<label> <span class="text-darker ff--primary fw--500">City</span>

									<select name="city" class="form-control form-sm"
									data-trigger="selectmenu" style="display: none;">
										<option value="newyork">New York</option>
										<option value="California">California</option>
										<option value="Atlanta">Atlanta</option>
								</select>
									<div class="custom-select-menu" tabindex="0">
										<span class="selection-made">New York</span>
										<ul data-select-name="city" style="display: none;">
											<li data-option-value="newyork" class="selected">New
												York</li>
											<li data-option-value="California">California</li>
											<li data-option-value="Atlanta">Atlanta</li>
										</ul>
										<input type="hidden" name="city" value="newyork">
									</div>
								</label>
							</div>
						</div>

						<div class="col-xs-12">
							<div class="form-group">
								<label> <span class="text-darker ff--primary fw--500">Filter
										Country</span> <select name="city" class="form-control form-sm"
									data-trigger="selectmenu" style="display: none;">
										<option value="unitedstates">United States</option>
										<option value="australia">Australia</option>
										<option value="turkey">Turkey</option>
										<option value="vietnam">Vietnam</option>
								</select>
									<div class="custom-select-menu" tabindex="0">
										<span class="selection-made">United States</span>
										<ul data-select-name="city" style="display: none;">
											<li data-option-value="unitedstates" class="selected">United
												States</li>
											<li data-option-value="australia">Australia</li>
											<li data-option-value="turkey">Turkey</li>
											<li data-option-value="vietnam">Vietnam</li>
										</ul>
										<input type="hidden" name="city" value="unitedstates">
									</div>
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
		</div>
		<div class="widget">
			<h2 class="h4 fw--700 widget--title">Notice</h2>

			<!-- Text Widget Start -->
			<div class="text--widget">
				<p>There are many variations of passages of Lorem Ipsum
					available, but the majority have suffered alteration in some look
					even slightly believable.</p>
			</div>
			<!-- Text Widget End -->
		</div>
		<div class="widget">
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
		</div>
		<div class="widget">
			<h2 class="h4 fw--700 widget--title">Archives</h2>

			<!-- Nav Widget Start -->
			<div class="nav--widget">
				<ul class="nav">
					<li><a href="#"> <i class="fa fa-calendar-o"></i> <span
							class="text">Jan - July 2017</span> <span class="count">(86)</span>
					</a></li>
					<li><a href="#"> <i class="fa fa-calendar-o"></i> <span
							class="text">Jan - Dce 2016</span> <span class="count">(328)</span>
					</a></li>
					<li><a href="#"> <i class="fa fa-calendar-o"></i> <span
							class="text">Jan - Dec 2015</span> <span class="count">(427)</span>
					</a></li>
				</ul>
			</div>
			<!-- Nav Widget End -->
		</div>
		<div class="widget">
			<h2 class="h4 fw--700 widget--title">Advertisements</h2>

			<!-- Ad Widget Start -->
			<div class="ad--widget">
				<a href="#"> <img src="img/widgets-img/ad.jpg" alt=""
					class="center-block">
				</a>
			</div>
			<!-- Ad Widget End -->
		</div>

	</div>

