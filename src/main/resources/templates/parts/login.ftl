<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group">
            <label for="exampleInputEmail1" > User Name : </label>
            <div class="col-sm-5">
                <input type="text" name="username" class="form-control" placeholder="User name"/>
            </div>


        </div>

        <div class="form-group">
            <label  for="exampleInputPassword1"> Password: </label>
            <div class="col-sm-5">
                <input type="password" name="password"  class="form-control" placeholder="Password"/>
            </div>
        </div>

        <#if isRegisterForm>
        <div class="form-group">
            <label  for="exampleInputEmail1"> Email: </label>
            <div class="col-sm-5">
                <input type="email" name="email"  class="form-control" placeholder="some@some.com"/>
            </div>
        </div>
        </#if>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <#if !isRegisterForm><a href="/registration">Add new user</a></#if>
        <button class="btn btn-primary" type="submit"><#if isRegisterForm>Create<#else>Sign In</#if></button>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-primary" type="submit">Sign Out</button>
    </form>
</#macro>