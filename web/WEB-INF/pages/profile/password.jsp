<h2>Change password</h2>
<form action="${root}profile/password" method="POST">
    <div class="input-group">
        <label for="oldPassword">Old Password</label>
        <input type="password" required id="oldPassword" name="oldPassword" class="form-control">
    </div>
    <div class="input-group">
        <label for="newPassword">New Password</label>
        <input type="password" required id="newPassword" name="newPassword" class="form-control">
    </div>
    <div class="input-group">
        <label for="newPassword2">New Password2</label>
        <input type="password" required id="newPassword2" name="newPassword2" class="form-control">
    </div>

    <button>Submit</button>
</form>
