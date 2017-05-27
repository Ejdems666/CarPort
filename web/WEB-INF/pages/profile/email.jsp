<%@ page import="static org.cba.Path.ROOT" %>
<h2>Change username/email</h2>
<form action="<%=ROOT%>profile/email" method="POST">
    <div class="input-group">
        <label for="email">Email</label>
        <input type="email" required id="email" name="email" class="form-control">
    </div>

    <button>Submit</button>
</form>
