<h2>change password</h2>
<form action="${root}profile/password" method="post">
    <div class="input-group">
        <label for="oldpassword">old password</label>
        <input type="password" required id="oldpassword" name="oldPassword" class="form-control">
    </div>
    <div class="input-group">
        <label for="newpassword">new password</label>
        <input type="password" required id="newpassword" name="newPassword" class="form-control">
    </div>
    <div class="input-group">
        <label for="newpassword2">new password2</label>
        <input type="password" required id="newpassword2" name="newPassword2" class="form-control">
    </div>

    <button>submit</button>
</form>
