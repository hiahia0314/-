import { useState, useEffect } from 'react';
import { Button, Descriptions, Input } from 'antd';
import './LoginPage'
import LoginPage from './LoginPage';

var items = [
  {
    label: '账号',
    children: '',
  },
  {
    label: '密码',
    span: 'filled',
    // span = 2
    children: '',
  },
  {
    label: '昵称',
    span: 'filled',
    // span = 3
    children: '',
  },
  {
    label: '好友',
    span: 1,
    // span will be 3 and warning for span is not align to the end
    children: '',
  },
];

function Settings() {
    const [isLogin, setIsLogin] = useState(false);

    useEffect(() => {
      //获取本地存储的用户信息
      const userInfo = JSON.parse(localStorage.getItem('userInfo'));
      console.log(userInfo);
      if(userInfo) {
        setIsLogin(true);
        items = [
          {
            label: '账号',
            children: userInfo.account,
          },
          {
            label: '密码',
            span: 'filled',
            children: <Input.Password value={userInfo.password} disabled />,
          },
          {
            label: '昵称',
            children: userInfo.name
          },
          {
            label: '操作',
            span: 'filled',
            children: <Button danger onClick={logout}>退出登录</Button>
          },
        ];
      }
    },[])

    const logout = () => {
      //清除本地存储的用户信息
      localStorage.removeItem('userInfo');
      setIsLogin(false);
    }

    return (
      <div className="settings">
        {!isLogin ? 
          <LoginPage isLogin={isLogin} setIsLogin={setIsLogin} />
          :
          <Descriptions bordered title="账号信息" items={items} />
        }
      </div>
    )
  }
  
  export default Settings