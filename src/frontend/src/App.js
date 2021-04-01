import { useState, useEffect } from 'react';
import './App.css';

import {getAllPeople, deletePerson} from "./client";
import { Layout, Spin, Empty, Menu, Breadcrumb, Table,
    Button, Badge, Tag, Avatar, Radio, Popconfirm } from 'antd';
import {
    DesktopOutlined, PieChartOutlined, FileOutlined,
    TeamOutlined, UserOutlined, LoadingOutlined, PlusOutlined
} from '@ant-design/icons';
import PersonDrawerForm from "./PersonDrawerForm";
import {successNotification, errorNotification} from "./Notification";

const { Header, Content, Footer, Sider } = Layout;
const { SubMenu } = Menu;
const TheAvatar = ({name}) => {
    let trim = name.trim();
    if (trim.length === 0) {
        return <Avatar icon={<UserOutlined/>}/>
    }
    const split = trim.split(" ");
    if (split.length === 1) {
        return <Avatar>{name.charAt(0)}</Avatar>
    }
    return <Avatar>{`${name.charAt(0)}${name.charAt(name.length - 1)}`}</Avatar>
}
const removePerson = (studentId, callback) => {
    deletePerson(studentId).then(() => {
        successNotification( "Person deleted", `Person with id ${studentId} was deleted`);
        callback();
    }).catch(err => {
        err.response.json().then(res => {
            console.log(res);
            errorNotification(
                "There was an issue",
                `${res.message} [${res.status}] [${res.error}]`
            );
        });
    });
}

const columns = fetchPeople => [
    {
        title: '',
        dataIndex: 'avatar',
        key: 'avatar',
        render: (text, person) => <TheAvatar name={person.name}/>
    },
    {
        title: 'Id',
        dataIndex: 'id',
        key: 'id',
    },
    {
        title: 'Name',
        dataIndex: 'name',
        key: 'name',
    },
    {
        title: 'Email',
        dataIndex: 'email',
        key: 'email',
    },
    {
        title: 'Gender',
        dataIndex: 'gender',
        key: 'gender',
    },
    {
        title: 'Actions',
        key: 'actions',
        render: (text, person) =>
            <Radio.Group>
                <Popconfirm
                    placement='topRight'
                    title={`Are you sure to delete ${person.name}`}
                    onConfirm={() => removePerson(person.id, fetchPeople)}
                    okText='Yes'
                    cancelText='No'>
                    <Radio.Button value="small">Delete</Radio.Button>
                </Popconfirm>
                <Radio.Button value="small">Edit</Radio.Button>
            </Radio.Group>
    }
];
const antIcon = <LoadingOutlined style={{ fontSize: 24 }} spin />;

function App() {

    const [people, setPeople] = useState([]);
    const [collapsed, setCollapsed] = useState(false);
    const [fetching, setFetching] = useState(true);
    const [showDrawer, setShowDrawer] = useState(false);

    const fetchPeople = () => getAllPeople()
            .then(res => res.json())
            .then(data => {
                setPeople(data);
            }).catch(err => {
                err.response.json().then(res => {
                    errorNotification(
                        "There was an issue",
                        `${res.message} statusCode:[${res.status}]`
                    )
                });
            }).finally(() =>  setFetching(false));

    useEffect(() => {
        fetchPeople();
    }, []);

    const renderPeople = () => {
        if (fetching) {
            return <Spin indicator={antIcon} />;
        }
        if (people.length <= 0) {
            return <>
                <Button
                    onClick={() => setShowDrawer(!showDrawer)}
                    type="primary" shape="round" icon={<PlusOutlined/>} size="small">
                    Add Person
                </Button>
                <PersonDrawerForm
                    showDrawer={showDrawer}
                    setShowDrawer={setShowDrawer}
                    fetchStudents={fetchPeople}
                />
                <Empty/>
            </>
        }
        return <>
            <PersonDrawerForm
                showDrawer={showDrawer}
                setShowDrawer={setShowDrawer}
                fetchPeople={fetchPeople}
            />
            <Table
                dataSource={people}
                columns={columns(fetchPeople)}
                bordered
                title={() =>
                    <>
                        <Button
                            onClick={() => setShowDrawer(!showDrawer)}
                            type="primary" shape="round" icon={<PlusOutlined />} size="medium">
                            Add Person
                        </Button>
                        <br/><br/>
                        <Tag>Number of students</Tag>
                        <Badge count={people.length} className="site-badge-count-4"/>
                    </>}
                pagination={{ pageSize: 50 }}
                scroll={{ y: 550 }}
                rowKey={(people) => people.id}
            />
        </>
    }

    return <Layout style={{ minHeight: '100vh' }}>
        <Sider collapsible collapsed={collapsed} onCollapse={setCollapsed}>
            <div className="logo" />
            <Menu theme="dark" defaultSelectedKeys={['1']} mode="inline">
                <Menu.Item key="1" icon={<PieChartOutlined />}>
                    Option 1
                </Menu.Item>
                <Menu.Item key="2" icon={<DesktopOutlined />}>
                    Option 2
                </Menu.Item>
                <SubMenu key="sub1" icon={<UserOutlined />} title="User">
                    <Menu.Item key="3">Tom</Menu.Item>
                    <Menu.Item key="4">Bill</Menu.Item>
                    <Menu.Item key="5">Alex</Menu.Item>
                </SubMenu>
                <SubMenu key="sub2" icon={<TeamOutlined />} title="Team">
                    <Menu.Item key="6">Team 1</Menu.Item>
                    <Menu.Item key="8">Team 2</Menu.Item>
                </SubMenu>
                <Menu.Item key="9" icon={<FileOutlined />}>
                    Files
                </Menu.Item>
            </Menu>
        </Sider>
        <Layout className="site-layout">
            <Header className="site-layout-background" style={{ padding: 0 }} />
            <Content style={{ margin: '0 16px' }}>
                <Breadcrumb style={{ margin: '16px 0' }}>
                    <Breadcrumb.Item>User</Breadcrumb.Item>
                    <Breadcrumb.Item>Bill</Breadcrumb.Item>
                </Breadcrumb>
                <div className="site-layout-background" style={{ padding: 24, minHeight: 360 }}>
                    {renderPeople()}
                </div>
            </Content>
            <Footer style={{ textAlign: 'center' }}>Pibox ©2021</Footer>
        </Layout>
    </Layout>

}

export default App;
