import { Badge, Calendar, Drawer, Popconfirm, Table, Button, Form, Input, Radio } from "antd"
import { useState, useEffect } from "react"
import { useNavigate } from "react-router-dom"

import './MyCalendar.css'

const SERVER_URL = 'http://127.0.0.1:8080';


/*  
<Badge status="success" text="Success" />
<Badge status="error" text="Error" />
<Badge status="default" text="Default" />
<Badge status="processing" text="Processing" />
<Badge status="warning" text="Warning" />
*/
//请求接口获取数据
let LISTDATA = [
  {
    date: '2024-12-20',
    events: [
      {
        uid: new Date().getTime() - 1,
        type: 'warning',
        title: 'Meeting',
        content: 'This is warning event.',
      },
      {
        uid: new Date().getTime() - 2,
        type: 'success',
        title: 'Holiday',
        content: 'This is usual event.',
      },
    ]
  },
  {
    date: '2024-12-24',
    events: [
      {
        uid: new Date().getTime() - 3,
        type: 'warning',
        title: 'Meeting',
        content: 'This is warning event.',
      },
      {
        uid: new Date().getTime() - 4,
        type: 'success',
        title: 'Holiday',
        content: 'This is usual event.',
      },
      {
        uid: new Date().getTime() - 5,
        type: 'error',
        title: 'Working',
        content: 'This is error event.',
      },
    ]
  },
  {
    date: '2024-12-28',
    events: [
      {
        uid: new Date().getTime() - 6,
        type: 'warning',
        title: 'Meeting',
        content: 'This is warning event',
      },
      {
        uid: new Date().getTime() - 7,
        type: 'success',
        title: 'Holiday',
        content: 'This is very long usual event......',
      },
      {
        uid: new Date().getTime() - 8,
        type: 'error',
        title: 'Working',
        content: 'This is error event 1.',
      },
      {
        uid: new Date().getTime() - 9,
        type: 'error',
        title: 'Working',
        content: 'This is error event 2.',
      },
      {
        uid: new Date().getTime() - 10,
        type: 'error',
        title: 'Working',
        content: 'This is error event 3.',
      },
      {
        uid: new Date().getTime() - 11,
        type: 'error',
        title: 'Working',
        content: 'This is error event 4.',
      },
    ]
  }
]

function MyCalendar() {
  const [selectDate, setSelectDate] = useState('');
  const [openDate, setOpenDate] = useState(false);//一级抽屉
  const [openEventAdder, setOpenEventAdder] = useState(false);//二级抽屉
  const [listData, setListData] = useState(LISTDATA);//默认为静态的测试数据
  const [events, setEvents] = useState([]);

  const [form] = Form.useForm();
  const navigate = useNavigate();

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem('userInfo'));
    if (user) {
      //fetch listData数据库数据
      fetchData(user);
    } else {
      //跳转到settings页面
      alert('请先登录!');
      navigate('/settings', { replace: true });
    }
  }, [navigate])

  //fetch listData
  const fetchData = async (user) => {
    fetch(SERVER_URL + '/event/' + `${user.account}`, {
      method: 'GET',
      headers: { "Content-Type": "application/json", 'Access-Control-Allow-Origin': '*' },
    })
      .then(res => res.json())
      .then(data => {
        let msgObj = data;
        console.log("获取数据", msgObj);

        // if(msgObj.success){
        //   setListData(msgObj.listData);
        // }else{
        //     alert("获取数据失败:" + msgObj.message);
        // }

        setListData(msgObj.data);
      })
  }

  const getEventData = (value) => {
    let eventData = [];
    //遍历LISTDATA 找到对应日期的数据
    for (let i = 0; i < listData.length; i++) {
      if (listData[i].date === value.format('YYYY-MM-DD')) {
        eventData = listData[i].events;
        break;
      }
    }
    return eventData || [];
  };

  const getMonthData = (value) => {
    if (value.month() === 8) {
      return 1394;
    }
  };

  const monthCellRender = (value) => {
    const num = getMonthData(value);
    return num ? (
      <div className="notes-month">
        <section>{num}</section>
        <span>Backlog number</span>
      </div>
    ) : null;
  };

  const dateCellRender = (value) => {
    const listData = getEventData(value);
    return (
      <ul className="event-list">
        {listData.map((item) => (
          <li key={item.uid}>
            <Badge status={item.type} text={item.title} />
          </li>
        ))}
      </ul>
    );
  };

  const cellRender = (current, info) => {
    //console.log(current);
    if (info.type === 'date') return dateCellRender(current);
    if (info.type === 'month') return monthCellRender(current);
    return info.originNode;
  };

  //点击某日期的回调函数
  const onSelect = (value, { source }) => {
    let dateValue = value.format('YYYY-MM-DD');
    console.log(dateValue);
    setSelectDate(dateValue);
    //打开Drawer
    setOpenDate(true);
    //设置当前date的events
    let d = listData.find((item) => item.date === dateValue);
    console.log(d);
    if (d) {
      setEvents(d.events);
    } else {
      setEvents([]);
    }
  }

  //表格的默认列标题
  const COLOMNS = [
    {
      title: '标题',
      dataIndex: 'title',
      key: 'title',
    },
    {
      title: '内容',
      dataIndex: 'description',
      width: '70%',
      key: 'description',
    },
    {
      title: '操作',
      key: 'action',
      render: (_, record) =>
        LISTDATA.length >= 1 ? (
          <Popconfirm title="确认删除?" onConfirm={() => handleDelete(record)} okText="确认" cancelText="取消" >
            <Button type="link">删除</Button>
          </Popconfirm>
        ) : null,
    }
  ]

  //表格中每行的删除按钮的回调函数
  const handleDelete = (record) => {
    const newEvents = events.filter((item) => (item.uid !== record.uid));
    const newListData = listData.map((item) => {
      if (item.date === selectDate) {
        return {
          ...item,
          events: newEvents,
        };
      }
      return item;
    });
    //fetch 数据库删除该event
    fetch(SERVER_URL + "/event/" + `${record.uid}`, {
      method: "DELETE",
      headers: { "Content-Type": "application/json", 'Access-Control-Allow-Origin': '*' },
    })
      .then(res => res.json())
      .then(data => {
        let jsonObj = data;
        console.log("后端返回msg", jsonObj)

        if (jsonObj.isSuccess == "success") {
          //删除成功
          console.log("删除成功");
          setEvents(newEvents);
          setListData(newListData);
        } else {
          //删除失败
          alert("删除失败:" + jsonObj.msg);
        }
      })
  };

  //表格中的确认添加按钮的回调函数
  const handleAdd = (values) => {
    const newEvent = { ...values, uid: new Date().getTime() };
    console.log(newEvent);
    const newEvents = [...events, newEvent];
    const newListData = listData.map((item) => {
      if (item.date === selectDate) {
        return {
          ...item,
          events: newEvents,
        };
      }
      return item;
    })
    const user = JSON.parse(localStorage.getItem('userInfo'));
    //fetch 数据库添加该event
    fetch(SERVER_URL + "/event", {
      method: "POST",
      body: JSON.stringify({ ...newEvent, "user": user.account, "date": selectDate }),
      headers: { "Content-Type": "application/json", 'Access-Control-Allow-Origin': '*' },
    })
      .then(res => res.json())
      .then(data => {
        let jsonObj = data;
        console.log("后端返回msg", jsonObj)

        if (jsonObj.isSuccess == "success") {
          //添加成功
          console.log("添加成功:" + newEvent);
          setEvents(newEvents);
          setListData(newListData);
        } else {
          //注册失败
          alert("添加失败:" + jsonObj.msg);
        }
      })

    //清空Form并关闭二级Drawer
    form.resetFields();
    setOpenEventAdder(false);
  }

  return (
    <div>
      <Calendar cellRender={cellRender} onSelect={onSelect} />

      <Drawer title={selectDate ? selectDate : ""} width={720} closable={true} open={openDate} onClose={() => setOpenDate(false)}>

        <Button
          onClick={() => setOpenEventAdder(true)}
          type="primary"
          style={{
            marginBottom: 16,
          }}>添加事件</Button>

        <Table
          rowKey="key"
          bordered
          columns={COLOMNS}
          dataSource={events.map((item, index) => ({ ...item, key: index }))}
        />

        <Drawer title={'添加新事件'} width={520} closable={true} open={openEventAdder} onClose={() => setOpenEventAdder(false)}>
          <Form
            form={form}
            name="newEvent"
            labelCol={{
              span: 8,
            }}
            wrapperCol={{
              span: 16,
            }}
            style={{
              transform: 'translateX(-20%)',
            }}
            initialValues={{
              remember: true,
            }}
            onFinish={handleAdd}
            autoComplete="off"
          >
            <Form.Item
              label="类型"
              name="type"
              rules={[
                {
                  required: true,
                  message: '请选择类型!',
                },
              ]}
            >
              <Radio.Group defaultValue="a" buttonStyle="solid" size="small">
                <Radio.Button value="success"><Badge status="success" text="已完成" /></Radio.Button>
                <Radio.Button value="error"><Badge status="error" text="错误" /></Radio.Button>
                <Radio.Button value="default"><Badge status="default" text="默认" /></Radio.Button>
                <Radio.Button value="processing"><Badge status="processing" text="进行中" /></Radio.Button>
                <Radio.Button value="warning"><Badge status="warning" text="警告" /></Radio.Button>
              </Radio.Group>
            </Form.Item>

            <Form.Item
              label="标题"
              name="title"
              rules={[
                {
                  required: true,
                  message: '请输入标题!',
                },
              ]}
            >
              <Input showCount maxLength={10} />
            </Form.Item>

            <Form.Item
              label="内容"
              name="description"
              rules={[
                {
                  required: true,
                  message: '请输入内容!',
                },
              ]}
            >
              <Input.TextArea showCount maxLength={200} autoSize={{ minRows: 4, maxRows: 10 }} />
            </Form.Item>

            <Form.Item label={null}>
              <Button type="primary" htmlType="submit">
                提交
              </Button>
            </Form.Item>
          </Form>
        </Drawer>

      </Drawer>
    </div>
  );
}

export default MyCalendar
