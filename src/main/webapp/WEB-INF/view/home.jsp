<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Hustle</title>
<link rel="stylesheet" href="<c:url value='/css/main.css'/>" />
<link rel="stylesheet" href="<c:url value='/css/style.css'/>" />
<link rel="stylesheet" href="<c:url value='/css/sidebar-themes.css'/>" />
<link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>" />
<link rel="stylesheet" href="<c:url value='/css/fontawesome-all.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/css/jquery.mCustomScrollbar.min.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/css/bootstrap-table.min.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/css/jquery.dataTables.min.css'/>" />
<script src="<c:url value='/js/jquery.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/js/main.js'/>" type="text/javascript"></script>
<script src="<c:url value='/js/bootstrap.bundle.min.js'/>"
	type="text/javascript"></script>
<script src="<c:url value='/js/popper.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/js/jquery.mCustomScrollbar.concat.min.js'/>"
	type="text/javascript"></script>
<script src="<c:url value='/js/jquery.dataTables.min.js'/>"
	type="text/javascript"></script>
<script src="<c:url value='/js/bootstrap-table.min.js'/>"
	type="text/javascript"></script>
<style type="text/css">
#dweller-summary-table table tr:hover td {
	background-color: palegoldenrod;
}
</style>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$(
								"nav .sidebar-content .sidebar-menu ul li .sidebar-submenu ul li a")
								.on('click', function(e) {
									e.preventDefault();
									//alert( $(this).prop('nodeName'));
									//var href = $(this).attr('href');
									//alert(href);
									$(this).tab('show');
								});

						$('a[data-toggle="tab"]').on('shown.bs.tab',
								function(e) {
									//e.target // newly activated tab
									$(this).relatedTarget.tab('hide'); // previous active tab
								});
						$("#upload-success").hide();
						$("#upload-failed").hide();
						$(document).on(
								'submit',
								'form.dweller-data',
								function(e) {
									e.preventDefault();
									var data = new FormData(this);
									jQuery.ajax({
										url : 'dwellers/upload',
										data : data,
										cache : false,
										contentType : false,
										processData : false,
										method : 'POST',
										success : function(data) {
											$("#upload-success").alert();
											$("#upload-success").fadeTo(2000,
													500).slideUp(
													500,
													function() {
														$("#upload-success")
																.slideUp(500);
													});
										},
										error : function() {
											$("#upload-failed").alert();
											$("#upload-failed").fadeTo(2000,
													500).slideUp(
													500,
													function() {
														$("#upload-failed")
																.slideUp(500);
													});
										}
									});
								});

						$('#show-dweller-summary').on('click', function(e) {
							e.preventDefault();
							jQuery.ajax({
								url : 'dwellers/summary',
								cache : false,
								contentType : false,
								processData : false,
								method : 'GET',
								success : function(data) {
									/*
									$('#dweller-summary-table')
											.bootstrapTable(
													{
														columns : [
																{
																	field : 'id',
																	title : 'ID'
																},
																{
																	field : 'name',
																	title : 'Name',
																	sorting: true
																} ],
														data : data,
														pagination : true,
														pageSize : 10,
														pageList : [
																10,
																25,
																50,
																100 ],
														paginationHAlign : 'right',
														paginationVAlign : 'bottom',
														paginationDetailHAlign : 'left',
														sortable : false,
														classes : 'table table-striped table-bordered table-sm',
														undefinedText : 'N/A',
														striped : true,
														showColumns : true,
														showRefresh : true,
													});
									 */
									$('#dweller-summary-table').dataTable({
										"data" : data,
										columns : [ {
											title : "ID"
										}, {
											title : "Name"
										} ],
										"ordering" : true
									});
								}
							});
						});
					});
</script>
</head>
<body>
	<div class="page-wrapper default-theme sidebar-bg bg1 toggled">
		<nav id="sidebar" class="sidebar-wrapper">
			<div class="sidebar-content">
				<div class="sidebar-item sidebar-brand">
					<a href="#">Hustle</a>
				</div>
				<!-- sidebar-menu  -->
				<div class=" sidebar-item sidebar-menu">
					<ul>
						<li class="header-menu"><span>Hustle Menu</span></li>
						<li class="sidebar-dropdown"><a href="#"> <i
								class="fa fa-tachometer-alt"></i> <span class="menu-text">Home</span>
						</a>
							<div class="sidebar-submenu">
								<ul>
									<li><a href="#home-home">home</a></li>
								</ul>
							</div></li>
						<!-- Dweller -->
						<li class="sidebar-dropdown"><a href="#"> <i
								class="fa fa-tachometer-alt"></i> <span class="menu-text">Dweller</span>
						</a>
							<div class="sidebar-submenu">
								<ul>
									<li><a href="#dweller-home">Home</a></li>
								</ul>
								<ul>
									<li><a href="#dweller-upload">Upload data</a></li>
								</ul>
								<ul>
									<li><a href="#dweller-download">Downloads</a></li>
								</ul>
								<ul>
									<li><a href="#dweller-summary">Dweller Summary</a></li>
								</ul>
							</div></li>
						<!-- Weapons -->
						<li class="sidebar-dropdown"><a href="#"> <i
								class="fa fa-tachometer-alt"></i> <span class="menu-text">Weapons</span>
						</a>
							<div class="sidebar-submenu">
								<ul>
									<li><a href="#weapon-home">home</a></li>
								</ul>
								<ul>
									<li><a href="#weapon-upload">upload</a></li>
								</ul>
							</div></li>
						<!-- Armors -->
						<li class="sidebar-dropdown"><a href="#"> <i
								class="fa fa-tachometer-alt"></i> <span class="menu-text">Armors</span>
						</a>
							<div class="sidebar-submenu">
								<ul>
									<li><a href="#armor-home">home</a></li>
								</ul>
								<ul>
									<li><a href="#armor-upload">upload</a></li>
								</ul>
							</div></li>
						<!-- Rings -->
						<li class="sidebar-dropdown"><a href="#"> <i
								class="fa fa-tachometer-alt"></i> <span class="menu-text">Rings</span>
						</a>
							<div class="sidebar-submenu">
								<ul>
									<li><a href="#rings-home">home</a></li>
								</ul>
								<ul>
									<li><a href="#rings-upload">upload</a></li>
								</ul>
							</div></li>
						<!-- Amulets -->
						<li class="sidebar-dropdown"><a href="#"> <i
								class="fa fa-tachometer-alt"></i> <span class="menu-text">Amulets</span>
						</a>
							<div class="sidebar-submenu">
								<ul>
									<li><a href="#amulets-home">home</a></li>
								</ul>
								<ul>
									<li><a href="#amulets-upload">upload</a></li>
								</ul>
							</div></li>
						<!-- Items -->
						<li class="sidebar-dropdown"><a href="#"> <i
								class="fa fa-tachometer-alt"></i> <span class="menu-text">Items</span>
						</a>
							<div class="sidebar-submenu">
								<ul>
									<li><a href="#items-home">home</a></li>
								</ul>
								<ul>
									<li><a href="#items-upload">upload</a></li>
								</ul>
							</div></li>
						<!-- Suits -->
						<li class="sidebar-dropdown"><a href="#"> <i
								class="fa fa-tachometer-alt"></i> <span class="menu-text">Suits</span>
						</a>
							<div class="sidebar-submenu">
								<ul>
									<li><a href="#suits-home">home</a></li>
								</ul>
								<ul>
									<li><a href="#suits-upload">upload</a></li>
								</ul>
							</div></li>
						<li class="sidebar-dropdown"><a href="#"> <i
								class="fa fa-tachometer-alt"></i> <span class="menu-text">Admin</span>
						</a>
							<div class="sidebar-submenu">
								<ul>
									<li><a href="#backoffice">backoffice</a></li>
								</ul>
							</div></li>
					</ul>
				</div>
				<!-- sidebar-menu  -->
			</div>
			<div class="sidebar-footer">
				<small>&copy; Hustle <sup>TM</sup>, Indigenous Software Ltd,
					2019. &reg;
				</small>
			</div>
		</nav>
		<main class="page-content pt-2">
		<div id="overlay" class="overlay"></div>
		<div class="container-fluid p-5">
			<div class="row">
				<div class="form-group col-md-12"></div>
				<div class="form-group col-md-12">
					<a id="toggle-sidebar" class="btn btn-dark" href="#"> <span>Toggle
							Sidebar</span>
					</a> <a id="pin-sidebar" class="btn btn-dark" href="#"> <span>Pin
							Sidebar</span>
					</a>
				</div>
				<div class="alert alert-success alert-dismissible"
					id="upload-success" role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>Success!</strong> File uploaded successfully!!
				</div>
				<div class="alert-danger alert-dismissible" id="upload-failed"
					role="alert">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					<strong>Failed!</strong> File upload failed!!
				</div>
			</div>
		</div>
		<!-- page-wrapper -->
		<div class="tab-content">
			<div class="tab-pane" id="home-home" role="tabpanel"
				aria-labelledby="home-home-tab">
				<h2>Home</h2>
				<h3>home</h3>
				<p>Here you can update the game data and get analysis, summary
					reports. This will help you play the game better. Happy Gaming !!!
				</p>
			</div>
			<div class="tab-pane" id="dweller-home" role="tabpanel"
				aria-labelledby="dweller-home-tab">
				<h2>Dweller</h2>
				<h3>home</h3>
				<p>Here you can update data on dwellers and see the analysis and
					summary reports for dwellers.</p>
			</div>
			<div class="tab-pane" id="dweller-upload" role="tabpanel"
				aria-labelledby="dweller-upload-tab">
				<h2>Dweller</h2>
				<h3>upload data</h3>
				<p>Here you can upload data for dwellers.</p>
				<br />
				<form name="dweller-data" id="dweller-data" class="dweller-data"
					method="post" enctype="multipart/form-data">
					<label>Upload:</label> <input type="file" name="file" /> <input
						type="submit" value="Upload" />
				</form>
			</div>
			<div class="tab-pane" id="dweller-download" role="tabpanel"
				aria-labelledby="dweller-download-tab">
				<h2>Dweller</h2>
				<h3>download</h3>
				<p>Download the required file.</p>
				<ul>
					<li><a
						href="<c:url value='/download/dwellers/dwellers.xlsx'/>">
							Dweller data template </a></li>
				</ul>
			</div>
			<div class="tab-pane" id="dweller-summary" role="tabpanel"
				aria-labelledby="dweller-summary-tab">
				<h2>Dweller</h2>
				<h3>Dweller Summary</h3>
				<div style="float: right; width: 100%">
					<input id="show-dweller-summary" value="Show Dweller Summary" />
				</div>
				<table id="dweller-summary-table" class="display compact">
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
						</tr>
					</thead>
				</table>
			</div>
			<div class="tab-pane" id="weapon-home" role="tabpanel"
				aria-labelledby="weapon-home-tab">
				<h2>Weapon</h2>
				<h3>home</h3>
				<p>Here you can update data on weapons and see the analysis and
					summary reports for weapons.</p>
			</div>
			<div class="tab-pane" id="weapon-upload" role="tabpanel"
				aria-labelledby="weapon-upload-tab">
				<h2>Weapon</h2>
				<h3>upload</h3>
				<p>Here you can upload data for weapons.</p>
				<br />
				<form>
					<label>Upload:</label> <input type="file" />
				</form>
			</div>
			<div class="tab-pane" id="armor-home" role="tabpanel"
				aria-labelledby="armor-home-tab">
				<h2>Armor</h2>
				<h3>home</h3>
				<p>Here you can update data on armors and see the analysis and
					summary reports for armors.</p>
			</div>
			<div class="tab-pane" id="armor-upload" role="tabpanel"
				aria-labelledby="armor-upload-tab">
				<h2>Armor</h2>
				<h3>upload</h3>
				<p>Here you can upload data for armors.</p>
				<br />
				<form>
					<label>Upload:</label> <input type="file" />
				</form>
			</div>
			<div class="tab-pane" id="rings-home" role="tabpanel"
				aria-labelledby="rings-home-tab">
				<h2>Rings</h2>
				<h3>home</h3>
				<p>Here you can update data on rings and see the analysis and
					summary reports for rings.</p>
			</div>
			<div class="tab-pane" id="rings-upload" role="tabpanel"
				aria-labelledby="rings-upload-tab">
				<h2>Rings</h2>
				<h3>upload</h3>
				<p>Here you can upload data for rings.</p>
				<br />
				<form>
					<label>Upload:</label> <input type="file" />
				</form>
			</div>
			<div class="tab-pane" id="amulets-home" role="tabpanel"
				aria-labelledby="amulets-home-tab">
				<h2>Amulets</h2>
				<h3>home</h3>
				<p>Here you can update data on amulets and see the analysis and
					summary reports for amulets.</p>
			</div>
			<div class="tab-pane" id="amulets-upload" role="tabpanel"
				aria-labelledby="amulets-upload-tab">
				<h2>Amulets</h2>
				<h3>upload</h3>
				<p>Here you can upload data for amulets.</p>
				<br />
				<form>
					<label>Upload:</label> <input type="file" />
				</form>
			</div>
			<div class="tab-pane" id="items-home" role="tabpanel"
				aria-labelledby="items-home-tab">
				<h2>Items</h2>
				<h3>home</h3>
				<p>Here you can update data on items and see the analysis and
					summary reports for items.</p>
			</div>
			<div class="tab-pane" id="items-upload" role="tabpanel"
				aria-labelledby="items-upload-tab">
				<h2>Items</h2>
				<h3>upload</h3>
				<p>Here you can upload data for items.</p>
				<br />
				<form>
					<label>Upload:</label> <input type="file" />
				</form>
			</div>
			<div class="tab-pane" id="suits-home" role="tabpanel"
				aria-labelledby="suits-home-tab">
				<h2>Suits</h2>
				<h3>home</h3>
				<p>Here you can update data on suits and see the analysis and
					summary reports for items.</p>
			</div>
			<div class="tab-pane" id="suits-upload" role="tabpanel"
				aria-labelledby="suits-upload-tab">
				<h2>Suits</h2>
				<h3>upload</h3>
				<p>Here you can upload data for suits.</p>
				<br />
				<form>
					<label>Upload:</label> <input type="file" />
				</form>
			</div>
			<div class="tab-pane" id="backoffice" role="tabpanel"
				aria-labelledby="backoffice-tab">
				<h2>Admin</h2>
				<h3>backoffice</h3>
			</div>
		</div>
		</main>
	</div>
</body>
</html>