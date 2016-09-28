<html>
<head>
<title>Employees</title>
<!-- <script type="text/javascript" src="resources/js/jquery-3.1.0.js"></script> -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/mentorshipprogram.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		getEmployees();
	});
</script>
</head>
<body>
	<h2>Employees</h2>
	<div>
	<table id="employeeList" width="80%">
	</table>
	</div>
	<br>
	<div>
	<form action="" id="newEmployeeForm">
	<table>
		<thead>
		<tr>
		<td colspan="2">Add employee</td>
		</tr>
		</thead>
		<tbody>
		<tr>
		<td>First name</td>
		<td>Second name</td>
		</tr>
		<tr>
		<td><input type="text" name="employeeName" /></td>
		<td><input type="text" name="employeeSecondName" /></td>
		</tr>
		<tr>
		<td>Email</td>
		<td>Birthday(dd/mm/yyyy)</td>
		</tr>
		<tr>
		<td><input type="email" name="employeeEmail" /></td>
		<td><input type="date" name="employeeBirthday" /></td>
		</tr>
		</tbody>
		<tfoot>
		<tr>
		<td colspan="2"><input type="button" name="Submit" value="Add Employee" onclick="addEmployee()"/></td>
		</tr>
		</tfoot>
	</table>
	</form>
	</div>
</body>
</html>
