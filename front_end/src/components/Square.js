import { useState, useEffect } from 'react';
import { Button, Drawer, List, Input, Divider, Popconfirm } from 'antd';
import dayjs from 'dayjs';
import { useNavigate } from "react-router-dom"

const SERVER_URL = 'http://127.0.0.1:8080';

const FRIENDLIST = [
  {
    account: '123456789111',
    name: '小李',
    addTime: '2021-01-01',
  },
  {
    account: '123456789222',
    name: '小王',
    addTime: '2021-01-02',
  },
  {
    account: '123456789',
    name: '小张',
    addTime: '2021-01-03',
  },
]

const FRIENDREQUEST = [
  {
    account: '123456789111',
    name: 'F1',
    addTime: '2024-01-01',
  },
  {
    account: '123456789222',
    name: 'F2',
    addTime: '2024-01-02',
  },
  {
    account: '123456789333',
    name: 'F3',
    addTime: '2024-01-03',
  },
]

function Square() {
  const [openFriend, setOpenFriend] = useState(false);
  const [friendLoading, setFriendLoading] = useState(true);
  const [friendList, setFriendList] = useState(FRIENDLIST);
  const [friendID, setFriendID] = useState('');
  const [openFriendRequest, setOpenFriendRequest] = useState(false);
  const [friendRequest, setFriendRequest] = useState(FRIENDREQUEST);

  const navigate = useNavigate();

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem('userInfo'));
    if (user) {

    } else {
      //跳转到settings页面
      alert('请先登录!');
      navigate('/settings', { replace: true });
    }
  }, [navigate])

  //点击查看好友列表的回调函数
  const showFriendLoading = () => {
    setOpenFriend(true);
    setFriendLoading(true);
    const user = JSON.parse(localStorage.getItem('userInfo'));

    //fetch数据库获取好友列表
    fetch(`${SERVER_URL}/getFriend/${user.account}`, {
      method: "GET",
      headers: { "Content-Type": "application/json", 'Access-Control-Allow-Origin': '*' },
    })
      .then(res => res.json())
      .then(data => {
        let jsonObj = data
        console.log("后端返回msg", jsonObj)

        if (jsonObj.isSuccess === "success") {
          //传输成功
          const fL = jsonObj.data[0].data;
          setFriendList(fL);
          const fR = jsonObj.data[1].data;
          setFriendRequest(fR);
          //加载成功
          setFriendLoading(false);
        } else {
          //传输失败
          alert("传输失败:" + jsonObj.msg);
        }
      })
  };

  //点击添加好友的回调函数
  const addFriend = () => {
    const addTime = dayjs().format('YYYY-MM-DD');
    const user = JSON.parse(localStorage.getItem('userInfo'));

    //fetch数据库发送添加好友请求
    const body = { "applicant": user.account, "receiver": friendID, "date": addTime };
    console.log(body);

    fetch(SERVER_URL + "/addFriend", {
      method: "POST",
      body: JSON.stringify(body),
      headers: { "Content-Type": "application/json", 'Access-Control-Allow-Origin': '*' },
    })
      .then(res => res.json())
      .then(data => {
        let jsonObj = data
        console.log("后端返回msg", jsonObj)

        if (jsonObj.isSuccess === "success") {
          alert("添加成功");
          setFriendID("");
        } else {
          //添加失败
          alert("添加失败:" + jsonObj.msg);
        }
      });
  }

  const deleteFriend = (friendAccount) => {
    const user = JSON.parse(localStorage.getItem('userInfo'));
    //fetch数据库发送删除好友请求
    console.log(friendAccount);
    fetch(SERVER_URL + "/deleteFriend", {
      method: "POST",
      body: JSON.stringify({ "applicant": user.account, "receiver": friendAccount }),
      headers: { "Content-Type": "application/json", 'Access-Control-Allow-Origin': '*' },
    })
      .then(res => res.json())
      .then(data => {
        let jsonObj = data
        console.log("后端返回msg", jsonObj)

        if (jsonObj.isSuccess === "success") {
          //删除成功，friendList中删除此申请
          const newFriendList = friendList.filter((item) => item.account !== friendAccount);
          setFriendList(newFriendList);
        } else {
          //删除失败
          alert("删除失败:" + jsonObj.msg);
        }
      });
  }
  //接受或者拒绝好友请求
  const handleRequest = (friendAccount, choose) => {
    const user = JSON.parse(localStorage.getItem('userInfo'));
    //fetch数据库发送接受or拒绝好友请求
    fetch(SERVER_URL + "/handleApply", {
      method: "POST",
      body: JSON.stringify({ "applicant": friendAccount, "receiver": user.account, "status": choose === 'yes' ? "accept" : "rejected" }),
      headers: { "Content-Type": "application/json", 'Access-Control-Allow-Origin': '*' },
    })
      .then(res => res.json())
      .then(data => {
        let jsonObj = data
        console.log("后端返回msg", jsonObj)

        if (jsonObj.isSuccess === "success") {
          if (choose === 'yes') {
            //friendList中添加此好友
            const newFriendList = friendList.concat(friendRequest.filter((item) => item.account === friendAccount));
            setFriendList(newFriendList);
          } else if (choose === 'no') {

          }
          //friendRequest中删除此申请
          const newFriendRequest = friendRequest.filter((item) => item.account !== friendAccount);
          console.log(newFriendRequest);
          setFriendRequest(newFriendRequest);
        } else {
          //删除失败
          alert("处理失败:" + jsonObj.msg);
        }
      });
  }

  return (
    <div className="square">
      <Button type="primary" onClick={showFriendLoading}>
        查看好友
      </Button>
      <Drawer
        closable
        destroyOnClose
        title={<span> {"好友列表"} <Button type='link' style={{ position: 'absolute', right: '10px' }} onClick={() => setOpenFriendRequest(true)} >查看申请</Button> </span>}
        placement="right"
        open={openFriend}
        loading={friendLoading}
        onClose={() => setOpenFriend(false)}
      >
        <List
          itemLayout="horizontal"
          dataSource={friendList}
          renderItem={(item, index) => (
            <List.Item>
              <List.Item.Meta
                title={item.name}
                description={"添加于" + item.addTime}
              />
              <Popconfirm title="确认删除?" onConfirm={() => deleteFriend(item.account)} okText="确认" cancelText="取消" >
                <Button type="link">删除</Button>
              </Popconfirm>
            </List.Item>
          )}
          style={{
            height: '95%',
            overflow: 'auto',
          }}
        />

        <Input
          placeholder="输入添加的好友账号"
          variant="outlined"
          value={friendID}
          onChange={e => setFriendID(e.target.value)}
          style={{
            position: 'absolute',
            width: '60%',
            bottom: '10px',
          }}
        />

        <Button
          type="primary"
          style={{
            position: 'absolute',
            bottom: '10px',
            right: '10px',
          }}
          onClick={addFriend}
        >
          添加好友
        </Button>

        <Drawer
          closable
          destroyOnClose
          title={<p>当前收到的好友申请</p>}
          placement="right"
          open={openFriendRequest}
          onClose={() => setOpenFriendRequest(false)}
        >

          <List
            itemLayout="horizontal"
            dataSource={friendRequest}
            renderItem={(item, index) => (
              <List.Item>
                <List.Item.Meta
                  title={item.name + " 申请添加你为好友"}
                  description={"于" + item.addTime}
                />
                <Button shape='circle' variant="solid" color='primary' onClick={() => handleRequest(item.account, 'yes')}>√</Button>
                <Divider type="vertical" />
                <Button shape='circle' variant="solid" color='danger' onClick={() => handleRequest(item.account, 'no')}>x</Button>
              </List.Item>
            )}
            style={{
              height: '95%',
              overflow: 'auto',
            }}
          />

        </Drawer>

      </Drawer>
    </div>
  )
}

export default Square