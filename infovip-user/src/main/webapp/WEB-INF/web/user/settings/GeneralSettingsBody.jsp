<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/tlds/module-manager" prefix="module"%>
<%@ taglib uri="/tlds/translate" prefix="tr"%>

	<section class="page--wrapper pt--80 pb--20" style="transform: none;">
		<div class="container" style="transform: none;">
			<div class="row" style="transform: none;">
				<!-- Main Content Start -->
				<div
					class="col-md-8 pb--30 float--right float--md-none"
					style="position: relative; overflow: visible; box-sizing: border-box; min-height: 1px;">

								
								
							<!-- General Settings Form Start -->
                            <div class="pt--30 fs--14">
                                <form name="general-settings">
                                    <div class="row">
                                        <div class="col-md-10 pb--40">
                                            <h3 class="h4 pb--30"><tr:tr val="General Settings"></tr:tr></h3>

                                            <div class="row">
                                                
                                                <div class="col-xs-6 col-xxs-12">
                                                    <div class="form-group">
                                                        <label>
                                                            <span>First Name *</span>
                                                            <input type="text" name="firstName" class="form-control form-sm" required="">
                                                        </label>
                                                    </div>
                                                </div>

                                                <div class="col-xs-6 col-xxs-12">
                                                    <div class="form-group">
                                                        <label>
                                                            <span>Last Name *</span>
                                                            <input type="text" name="lastName" class="form-control form-sm" required="">
                                                        </label>
                                                    </div>
                                                </div>
                                                
                                                 <div class="col-xs-12">
                                                    <div class="form-group">
                                                        <label>
                                                            <span>Old password:</span>
                                                            <input type="password" name="oldPassword" class="form-control form-sm" required="">
                                                        </label>
                                                    </div>
                                                </div>
                                                
                                                 <div class="col-xs-6 col-xxs-12">
                                                    <div class="form-group">
                                                        <label>
                                                            <span>New password *</span>
                                                            <input type="password" name="userPassword" class="form-control form-sm" required="">
                                                        </label>
                                                    </div>
                                                </div>

                                                <div class="col-xs-6 col-xxs-12">
                                                    <div class="form-group">
                                                        <label>
                                                            <span>New password repeat *</span>
                                                            <input type="password" name="userPasswordRepeat" class="form-control form-sm" required="">
                                                        </label>
                                                    </div>
                                                </div>

                                                <div class="col-xs-12">
                                                    <div class="form-group">
                                                        <label>
                                                            <span>E-mail *</span>
                                                            <input type="email" name="userMail" class="form-control form-sm" required="">
                                                        </label>
                                                    </div>
                                                </div>

                                                <div class="col-xs-12">
                                                    <div class="form-group">
                                                        <label>
                                                            <span>Country *</span>
                                                            <input type="text" name="country" class="form-control form-sm" required="">
                                                        </label>
                                                    </div>
                                                </div>
                                                
                                                 <div class="col-xs-12">
                                                    <div class="form-group">
                                                        <label>
                                                            <span>County *</span>
                                                            <input type="text" name="county" class="form-control form-sm" required="">
                                                        </label>
                                                    </div>
                                                </div>
                                                
                                                
												<div class="col-xs-12">
                                                    <div class="form-group">
                                                        <label>
                                                            <span>City *</span>
                                                            <input type="text" name="city" class="form-control form-sm" required="">
                                                        </label>
                                                    </div>
                                                </div>
                                             
                                             	<div class="col-xs-12">
                                             		<div style="margin: 0 auto">
														<div class="g-recaptcha" data-sitekey="6LfkM40UAAAAALw5xeC2104gDB6ekEgNYkwmW_7V"></div>
													</div>
		                                            <button type="button" data-type="submit" class="btn btn-primary"><tr:tr val="Submit"></tr:tr></button>
        		                                </div>
                                               
                                            </div>
                                        </div>
                             
                                    </div>
                                </form>
                            </div>
                            <!-- General Settings Form  End -->

					
						
					</div>
					
					
				<!-- Main Sidebar Start -->
				<div class="main--sidebar col-md-4 pb--60">
					
					<!-- Widget End -->
					<div class="">
						<tiles:insertTemplate template="${Configuration.WEB_DIRECTORY}/user/settings/sidebar.jsp"></tiles:insertTemplate>  			
					</div>
				<!-- Main Sidebar End -->
					
				</div>
				<!-- Main Content End -->

			</div>
		</div>
	</section>
