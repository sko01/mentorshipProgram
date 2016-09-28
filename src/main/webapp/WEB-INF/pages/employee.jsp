<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html>
<head>
<%@ page isELIgnored="false" %>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<script type="text/javascript" src="../resources/js/mentorshipprogram.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		getEmployee();
	});
</script>
</head>
	<form action='' id="updateEmployeeForm" method="post">
	<div id="employeeInfo"></div>
	</form>
</body>
</html>