<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="modal-header">
	<h3 class="modal-title">
		<span class="text-lg-left">${user.username}</span>
	</h3>
</div>
<div class="modal-body">
	<div class="panel panel-body border-top-info">
		<p class="content-group-sm text-muted">In this panel is details
			about this user.</p>

		<div class="well">
			<dl class="dl-horizontal">
				<dt>ID</dt>
				<dd>${user.id}</dd>
				<dt>Username or CNP</dt>
				<dd>${user.username}</dd>
				<dt>Email</dt>
				<dd>${user.email}</dd>
				<dt>Account creation date</dt>
				<dd>${user.creationDate}</dd>
				<dt>Account expiration date</dt>
				<dd>${user.expiredDate}</dd>
				<dt>Enabled</dt>
				<dd>${user.enabled}</dd>
				<dt>Roles</dt>
				<dd>
					<c:forEach items="${user.roles}" var="role">
						<p>
							<b>${role.name}</b>
						</p>
					</c:forEach>
				</dd>
			</dl>
		</div>
		<div class="btn-group" style="margin-top: 5px">
			<c:if test="${user.enabled  == true }">
				<button type="button" class="btn btn-default"
					ng-click="disableAccount('${user.id}')">Disable account</button>
			</c:if>
			<c:if test="${user.enabled  == false}">
				<button type="button" class="btn btn-default"
					ng-click="enableAccount('${user.id}')">Enable account</button>
			</c:if>
			<button type="button" class="btn btn-danger"
				ng-click="deleteAccount('${user.id}')">Delete account</button>
			<button type="button" class="btn btn-primary" ng-click="cancel()">Cancel</button>
		</div>
	</div>
</div>
