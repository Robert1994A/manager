<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="panel panel-flat ng-scope">
	<div class="panel-heading">
		<h5 class="panel-title">Search in contracts</h5>
		<a class="heading-elements-toggle"><i class="icon-more"></i></a>
	</div>

	<div class="panel-body">
		<form action="#" class="main-search ng-pristine ng-valid">
			<div class="input-group content-group">
				<div class="has-feedback has-feedback-left">
					<input type="text" class="form-control input-xlg" value=""
						placeholder="Type a contract name">
					<div class="form-control-feedback">
						<i class="icon-search4 text-muted text-size-base"></i>
					</div>
				</div>

				<div class="input-group-btn">
					<button type="submit" class="btn btn-primary btn-xlg">Search</button>
				</div>
			</div>
		</form>
	</div>
</div>



<div class="panel panel-flat">
	<div class="panel-heading">
		<h5 class="panel-title">All contracts</h5>
	</div>

	<div class="panel-body">
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr class="border-solid">
						<th>#</th>
						<th>Name</th>
						<th>Description</th>
						<th>Published</th>
						<th>Published Date</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${contracts}" var="contract">
						<tr>
							<td><c:out value="${contract.id}" /></td>
							<td><c:out value="${contract.name}" /></td>
							<td><c:out value="${contract.description}" /></td>
							<td><c:out value="${contract.published}" /></td>
							<td><c:out value="${contract.publishedDate}" /></td>
							<td></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<ul class="pagination"
				style="margin-left: auto; margin-right: auto; float: none;">
				<li><a href="#">&lt;</a></li>
				<li class="active"><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li class="disabled"><a href="#">4</a></li>
				<li><a href="#">5</a></li>
				<li><a href="#">&gt;</a></li>
			</ul>
		</div>
	</div>
</div>


