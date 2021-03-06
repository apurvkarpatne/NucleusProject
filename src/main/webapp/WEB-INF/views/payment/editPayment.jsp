<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 12/21/2020
  Time: 8:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../../../resources/css/appstyles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        .required-field::after{
            content: "*";
            color: red;
        }

        .error{
            color: red;
        }
    </style>
    <title>Edit Payment</title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/navbar.jsp" />
    <form:form method="Post" action="edit" modelAttribute="editThisPayment">
    <div class="row m-3">
        <h3>Edit Payment Cash</h3>
        <div class="offset-9">
            <input type="submit" class="btn btn-primary" name ="action" value="Save">
            <input type="reset" class="btn btn-warning" value="Clear">
            <a class="btn btn-danger" href="<%= request.getContextPath()%>/payment/" role="button">Cancel</a>
        </div>
    </div>
    <div class="card mb-3">
        <div class="card body p-3">
            <div class="row">
                <div class="form-group col-sm-3">
                    <label class="font-weight-bold required-field">Loan Application Number</label>
                    <form:input cssClass="form-control" path="loanApplicationNumber" readonly="true"/>
                    <form:errors path="loanApplicationNumber" cssClass="error"/>
                </div>
                <div class="form-group col-sm-3 offset-4">
                    <label class="font-weight-bold required-field">Payment Code</label>
                    <form:input path="paymentCode" cssClass="form-control"/>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-sm-3">
                    <label class="font-weight-bold required-field">Payment Amount</label>
                    <form:input cssClass="form-control" path="paymentAmount"/>
                </div>
                <div class="form-group col-sm-3 offset-4">
                    <label class="font-weight-bold required-field">Payment Date</label>
                    <form:input path="paymentDate" type="date" cssClass="form-control"/>
                    <form:errors path="paymentDate" cssClass="error"><p class="error">Invalid Date</p></form:errors>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-sm-3">
                    <label class="font-weight-bold required-field">Payout Bank Account</label>
                    <form:input path="payoutBankAccount" cssClass="form-control"/>
                    <form:errors path="payoutBankAccount" cssClass="error"></form:errors>
                </div>
                <div class="form-group col-sm-3 offset-4">
                    <label class="font-weight-bold required-field">Customer Code</label>
                    <form:input path="customerCode" cssClass="form-control" readonly="true"/>
                    <form:errors path="customerCode" cssClass="error"></form:errors>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-sm-3">
                    <label class="font-weight-bold required-field">Remarks</label>
                    <form:textarea path="remarks" cssClass="form-control"/>
                </div>
                <div class="form-group col-sm-3 offset-4">
                    <label class="required-field font-weight-bold">Payment Channel</label>
                    <form:select path="paymentChannel" cssClass="form-control">
                        <form:option value="Bank Transfer"></form:option>
                        <form:option value="Cheque"></form:option>
                        <form:option value="Draft"></form:option>
                    </form:select>
                </div>
            </div>
        </div>
    </div>
</div>
</form:form>
</div>
</body>
</html>
