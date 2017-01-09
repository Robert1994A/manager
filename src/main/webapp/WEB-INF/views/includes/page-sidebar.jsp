<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<div class="sidebar sidebar-main sidebar-fixed">
	<div class="sidebar-content">

		<!-- User menu -->
		<div class="sidebar-user">
			<div class="category-content">
				<div class="media">
					<a href="#/profile" class="media-left"><img
						src="./resources/assets/images/demo/users/face11.jpg"
						class="img-circle img-sm" alt=""></a>
					<div class="media-body">
						<span class="media-heading text-semibold"><security:authorize
								access="isAuthenticated()">
								<security:authentication property="principal.username" />
							</security:authorize></span>
						<div class="text-size-mini text-muted">
							<i class="icon-pin text-size-small"></i> &nbsp;Craiova
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /user menu -->

		<!-- Main navigation -->
		<div class="sidebar-category sidebar-category-visible">
			<div class="category-content no-padding">
				<ul class="navigation navigation-main navigation-accordion"
					id="navbar">
					<!-- Main -->
					<li class="navigation-header"><span>Main</span> <i
						class="icon-menu" title="Main pages"></i></li>
					<li id="homePage"><a href="#/home"><i class="icon-home4"></i>
							<span>Home page</span></a></li>
					<!-- Profile -->
					<li><a class="has-ul"><i class="icon-user-plus"></i> <span>Profile</span></a>
						<ul class="hidden-ul" style="">
							<li><a href="#/profile">My profile</a></li>
							<li><a href="#/profile/personalData">Personal data</a></li>
							<li><a href="#/profile/legalParentFather">Legal parent
									father</a></li>
							<li><a href="#/profile/legalParentMother">Legal parent
									mother</a></li>
							<li><a href="#/profile/address">Address</a></li>
							<li><a href="#/profile/identityCard">Personal identity
									card</a></li>
							<li><a href="#/profile/previousHighSchool">Previous
									HighSchool</a></li>
							<li><a href="#/profile/previousFaculty">Previous Faculty</a></li>
							<li><a href="#/profile/personalFiles">Personal Files</a></li>
						</ul></li>
					<!-- /Profile -->

					<!-- User -->
					<li class="navigation-header"><span>User</span> <i
						class="icon-menu"></i></li>
					<li><a href="#/users"><i class="icon-home4"></i> <span>Users</span></a></li>
					<!-- /User -->

					<!-- Contract -->
					<li class="navigation-header"><span>Contract</span> <i
						class="icon-menu" title="Forms"></i></li>
					<li><a href="#/addContract"><i class="icon-home4"></i> <span>Add
								contract</span></a></li>
					<li><a href="#/contracts"><i class="icon-home4"></i> <span>Contracts</span></a></li>
					<!-- /contract -->

					<!-- /main -->
				</ul>
			</div>
		</div>
		<!-- /main navigation -->
	</div>
</div>