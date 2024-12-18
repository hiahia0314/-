import React, { useState } from 'react';
import { Form, Input, Button, Typography, Checkbox } from 'antd';

const { Title } = Typography;
const SERVER_URL = 'http://127.0.0.1:8080';
 
function LoginPage({isLogin, setIsLogin}) {
  const [user, setUser] = useState({account: '', password: '', name: ''});
  const [onSigninPage, setOnSigninPage] = useState(false);
 
  const handleLogin = () => {
    console.log("login",user);

    fetch(SERVER_URL + "/login", {
            method: "POST",
            body: JSON.stringify({"id":user.account, "password":user.password}),
            headers: {"Content-Type": "application/json",'Access-Control-Allow-Origin': '*'},
        })
        .then(res => res.json())
        .then(data => {
            let jsonObj = data
            console.log("后端返回msg",jsonObj)

            if(jsonObj.isSuccess === "success"){
                //登录成功，将用户信息保存在本地
                localStorage.setItem("userInfo", JSON.stringify({...user, "name": jsonObj.data.name}));
                setIsLogin(true);
            }else{
                //登录失败
                alert("登陆失败:" + jsonObj.msg);
            }
    })
  }
    const handleSignin = () => {
        console.log("signup",user);
        //临时代码
        // localStorage.setItem("userInfo", JSON.stringify(user));
        // setIsLogin(true);

        fetch(SERVER_URL + "/register", {
            method: "POST",
            body: JSON.stringify({"id":user.account, "password":user.password, "name":user.name}),
            headers: {"Content-Type": "application/json",'Access-Control-Allow-Origin': '*'},
        })
        .then(res => res.json())
        .then(data => {
            let jsonObj = data;
            console.log("后端返回msg",jsonObj)

            if(jsonObj.isSuccess === "success"){
                //注册成功
                localStorage.setItem("userInfo", JSON.stringify(user));
                setIsLogin(true);
            }else{
                //注册失败
                alert("注册失败:" + jsonObj.msg);
            }
        })
    }

  if(!onSigninPage)
    return (
        <div id="loginPage">
            <Form onFinish={handleLogin} style={{ width: 300, margin: "auto" }}>
                <Title level={3} style={{ textAlign: 'center', marginBottom: 20 }}>登录</Title>
                <Form.Item>
                    <Input
                        prefix={<i className="anticon anticon-user" />}
                        placeholder="账号"
                        value={user.account}
                        onChange={(e) => setUser({ ...user, account: e.target.value })}
                    />
                </Form.Item>
                <Form.Item>
                    <Input.Password
                        prefix={<i className="anticon anticon-lock" />}
                        type="password"
                        placeholder="密码"
                        value={user.password}
                        onChange={(e) => setUser({ ...user, password: e.target.value })}
                    />
                </Form.Item>
                <Form.Item name="remember" valuePropName="checked" label={null}>
                    <Checkbox>Remember me</Checkbox>
                </Form.Item>
                <Form.Item>
                    <Button type="primary" htmlType="submit" block>
                        登录
                    </Button>
                </Form.Item>
                <Form.Item>
                    <Button type="secondary" onClick={() => setOnSigninPage(true)}>
                        注册界面
                    </Button>
                </Form.Item>
            </Form>
        </div>
    )
    else
    return (
        <div id="signinPage">
            <Form onFinish={handleSignin} style={{ width: 300, margin: "auto"}}>
                <Title level={3} style={{ textAlign: 'center', marginBottom: 20 }}>注册</Title>
                <Form.Item>
                    <Input
                        prefix={<i className="anticon anticon-user" />}
                        placeholder="账号"
                        value={user.account}
                        onChange={(e) => setUser({ ...user, account: e.target.value })}
                    />
                </Form.Item>
                <Form.Item>
                    <Input.Password
                        prefix={<i className="anticon anticon-lock" />}
                        type="password"
                        placeholder="密码"
                        value={user.password}
                        onChange={(e) => setUser({ ...user, password: e.target.value })}
                    />
                </Form.Item>
                <Form.Item>
                    <Input
                        prefix={<i className="anticon anticon-user" />}
                        placeholder="昵称"
                        value={user.name}
                        onChange={(e) => setUser({ ...user, name: e.target.value })}
                    />
                </Form.Item>
                <Form.Item>
                    <Button type="primary" htmlType="submit" block>
                        注册
                    </Button>
                </Form.Item>
                <Form.Item>
                    <Button type="secondary" onClick={()=>setOnSigninPage(false)}>
                        登录界面
                    </Button>
                </Form.Item>
            </Form>
        </div>
    )
 
//   return (
//     <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
    //   <Form onSubmit={handleSubmit} style={{ width: 300 }}>
    //     <Title level={3} style={{ textAlign: 'center', marginBottom: 20 }}>登录</Title>
    //     <Form.Item>
    //       <Input
    //         prefix={<i className="anticon anticon-user" />}
    //         placeholder="账号"
    //         value={account}
    //         onChange={(e) => setAccount(e.target.value)}
    //       />
    //     </Form.Item>
    //     <Form.Item>
    //       <Input
    //         prefix={<i className="anticon anticon-lock" />}
    //         type="password"
    //         placeholder="密码"
    //         value={password}
    //         onChange={(e) => setPassword(e.target.value)}
    //       />
    //     </Form.Item>
    //     <Form.Item>
    //       <Checkbox checked={rememberMe} onChange={(e) => setRememberMe(e.target.checked)}>
    //         记住我
    //       </Checkbox>
    //     </Form.Item>
    //     <Form.Item>
    //       <Button type="primary" htmlType="submit" block>
    //         登录
    //       </Button>
    //     </Form.Item>
    //   </Form>
//     </div>
//   );
};
 
export default LoginPage;