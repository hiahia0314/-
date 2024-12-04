import React, { useState } from 'react';
import { AppstoreOutlined, CalendarOutlined, SettingOutlined, } from '@ant-design/icons';
import { Breadcrumb, Layout, Menu, theme } from 'antd';
import { useNavigate, Routes, Route } from 'react-router-dom'


import Calendar from './components/MyCalendar';
import Square from './components/Square';
import Settings from './components/Settings';

const { Header, Content, Sider } = Layout;

const items = [
  {
    key: '/calendar',
    icon: <CalendarOutlined />,
    label: '日程',
  },
  {
    key: '/square',
    label: '广场',
    icon: <AppstoreOutlined />,
  },
  {
    key: '/settings',
    label: '设置',
    icon: <SettingOutlined />,
  },
];


function App(){

  const [collapsed, setCollapsed] = useState(false);

  const {
    token: { colorBgContainer, borderRadiusLG },
  } = theme.useToken();

  const navigate = useNavigate();
  const clickMenu = (e) => {
    console.log(e.key);
    navigate(e.key, { replace: true });
  }

  return (
    <Layout
      style={{
        minHeight: '100vh',
      }}
    >
      <Sider collapsible collapsed={collapsed} onCollapse={(value) => setCollapsed(value)}>
        <div className="demo-logo-vertical" />
        <Menu theme="dark"  mode="vertical" items={items} onClick={clickMenu} />
      </Sider>
      <Layout>
        <Header
          style={{
            padding: 0,
            background: colorBgContainer,
          }}
        />
        <Content
          style={{
            margin: '0 16px',
          }}
        >
          <Breadcrumb
            style={{
              margin: '16px 0',
            }}
          >
          </Breadcrumb>
          <div
            style={{
              padding: 24,
              minHeight: '88vh',
              background: colorBgContainer,
              borderRadius: borderRadiusLG,
            }}
          >
            <Routes>
              <Route path="/calendar" element={<Calendar />} />
              <Route path="/square" element={<Square />} />
              <Route path="/settings" element={<Settings />} />
            </Routes>

          </div>
        </Content>
        {/* <Footer
          style={{
            textAlign: 'center',
          }}
        >
          Ant Design ©{new Date().getFullYear()} Created by Ant UED
        </Footer> */}
      </Layout>
    </Layout>
  );
};
export default App;
