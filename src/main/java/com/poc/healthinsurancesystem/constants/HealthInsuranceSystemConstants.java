package com.poc.healthinsurancesystem.constants;

public class HealthInsuranceSystemConstants {

	private HealthInsuranceSystemConstants() {

	}

	public static final String OPERATION_NEW_EMPLOYEE = "new employee";
	public static final String OPERATION_DEACTIVATE_EMPLOYEE = "deactivate employee";
	public static final String EMPTY = "";
	public static final String SELF = "self";
	
	public static final String NEW_EMPLOYEE_DEPENDANT_DETAILS_EMAIL_TEXT = "<html>\r\n" + 
			"	<body>\r\n" + 
			"		Dear ${employeeName},\r\n" + 
			"		<br/>\r\n" + 
			"		<p>Please provide the below dependant details for processing the insurance.</p>\r\n" + 
			"		<ul>\r\n" + 
			"			<li>Name</li>\r\n" + 
			"			<li>Date of Birth</li>\r\n" + 
			"			<li>Relation with Employee</li>\r\n" + 
			"		</ul>\r\n" + 
			"		<br/>\r\n" + 
			"		Thanks & Regards,<br/>\r\n" + 
			"		HR.\r\n" + 
			"	</body>\r\n" + 
			"</html>";
	
	public static final String NEW_EMPLOYEE_INSURANCE_EMAIL_TEXT = "<html>\r\n" + 
			"	<head>\r\n" + 
			"		<style>\r\n" + 
			"			table {\r\n" + 
			"				border-collapse: collapse;\r\n" + 
			"			}\r\n" + 
			"			\r\n" + 
			"			table,\r\n" + 
			"			td {\r\n" + 
			"				border : 1px solid black;\r\n" + 
			"				padding: 10px 5px 10px 5px;\r\n" + 
			"			}\r\n" + 
			"		</style>\r\n" + 
			"	</head>\r\n" + 
			"	<body>\r\n" + 
			"		Dear Insurance Team,\r\n" + 
			"		<br/>\r\n" + 
			"		<p>Please find below the details of newly added employee.</p>\r\n" + 
			"		<table>\r\n" + 
			"			<tr>\r\n" + 
			"				<td>Name</td>\r\n" + 
			"				<td>${employeeData.name}</td>\r\n" + 
			"			</tr>\r\n" + 
			"			<tr>\r\n" + 
			"				<td>ID</td>\r\n" + 
			"				<td>${employeeData.employeeId}</td>\r\n" + 
			"			</tr>\r\n" + 
			"			<tr>\r\n" + 
			"				<td>Date of Birth</td>\r\n" + 
			"				<td>${employeeData.dateOfBirth}</td>\r\n" + 
			"			</tr>\r\n" + 
			"			<tr>\r\n" + 
			"				<td>Date of Joining</td>\r\n" + 
			"				<td>${employeeData.dateOfJoining}</td>\r\n" + 
			"			</tr>\r\n" + 
			"		</table>\r\n" + 
			"		<br/>\r\n" + 
			"		Thanks & Regards,<br/>\r\n" + 
			"		HR.\r\n" + 
			"	</body>\r\n" + 
			"</html>";
	
	public static final String DEACTIVATE_EMPLOYEE = "<html>\r\n" + 
			"	<head>\r\n" + 
			"		<style>\r\n" + 
			"			table {\r\n" + 
			"				border-collapse: collapse;\r\n" + 
			"			}\r\n" + 
			"			\r\n" + 
			"			table,\r\n" + 
			"			td {\r\n" + 
			"				border : 1px solid black;\r\n" + 
			"				padding: 10px 5px 10px 5px;\r\n" + 
			"			}\r\n" + 
			"		</style>\r\n" + 
			"	</head>\r\n" + 
			"	<body>\r\n" + 
			"		Dear Insurance Team,\r\n" + 
			"		<br/>\r\n" + 
			"		<p>Please deactivate the below employee insurance.</p>\r\n" + 
			"		<table>\r\n" + 
			"			<tr>\r\n" + 
			"				<td>Name</td>\r\n" + 
			"				<td>${employeeData.name}</td>\r\n" + 
			"			</tr>\r\n" + 
			"			<tr>\r\n" + 
			"				<td>ID</td>\r\n" + 
			"				<td>${employeeData.employeeId}</td>\r\n" + 
			"			</tr>\r\n" + 
			"			<tr>\r\n" + 
			"				<td>Date of Birth</td>\r\n" + 
			"				<td>${employeeData.dateOfBirth}</td>\r\n" + 
			"			</tr>\r\n" +  
			"			<tr>\r\n" + 
			"				<td>Date of Leaving</td>\r\n" + 
			"				<td>${employeeData.dateOfLeaving}</td>\r\n" + 
			"			</tr>\r\n" + 
			"		</table>\r\n" + 
			"		<br/>\r\n" + 
			"		Thanks & Regards,<br/>\r\n" + 
			"		HR.\r\n" + 
			"	</body>\r\n" + 
			"</html>";
	
	public static final String ADD_DEPENDENTS= "<html>\r\n" + 
			"	<head>\r\n" + 
			"		<style>\r\n" + 
			"			.bold{\r\n" + 
			"				font-weight: bold;\r\n" + 
			"			}\r\n" + 
			"			table {\r\n" + 
			"				border-collapse: collapse;\r\n" + 
			"			}\r\n" + 
			"			\r\n" + 
			"			table,\r\n" + 
			"			th,\r\n" + 
			"			td {\r\n" + 
			"				border : 1px solid black;\r\n" + 
			"				padding: 10px 5px 10px 5px;\r\n" + 
			"			}\r\n" + 
			"		</style>\r\n" + 
			"	</head>\r\n" + 
			"	<body>\r\n" + 
			"		Dear Insurance Team,\r\n" + 
			"		<br/>\r\n" + 
			"		<p>Please find below details of dependents that need to be added for employee id : <span class=\"bold\">${dependentDetailsRequest.employeeId}</span></p>\r\n" + 
			"		<table>\r\n" + 
			"			<tr>\r\n" + 
			"				<th>Name</th>\r\n" + 
			"				<th>Date Of Birth</th>\r\n" + 
			"				<th>Relation with Employee</th>\r\n" + 
			"			</tr>\r\n" + 
			"			<#list dependentDetailsRequest.dependentDetails as dependantDetail>\r\n" + 
			"				<tr>\r\n" + 
			"					<td>${dependantDetail.name}</td>\r\n" + 
			"					<td>${dependantDetail.dateOfBirth}</td>\r\n" + 
			"					<td>${dependantDetail.relation}</td>\r\n" + 
			"				</tr>\r\n" + 
			"			</#list>	\r\n" + 
			"		</table>\r\n" + 
			"		<br/>\r\n" + 
			"		Thanks & Regards,<br/>\r\n" + 
			"		HR.\r\n" + 
			"	</body>\r\n" + 
			"</html>";
}
