import {Drawer, Input, Col, Select, Form, Row, Button} from 'antd';
import {addNewPerson} from "./client";
import LoadingOutlined from "@ant-design/icons";
import {useState} from "react";
import {Spin} from "antd";
import {successNotification, errorNotification} from "./Notification";

const {Option} = Select;
const antIcon = <LoadingOutlined style={{ fontSize: 24 }} spin />;

function PersonDrawerForm({showDrawer, setShowDrawer, fetchPeople}) {
    const onCLose = () => setShowDrawer(false);
    const [submitting, setSubmitting] = useState(false);

    const onFinish = person => {
        setSubmitting(true);
        addNewPerson(person)
            .then(() => {
                onCLose();
                fetchPeople();
                successNotification("Person was added", `${person.name} was added`)
            }).catch(err => {
                err.response.json().then(res => {
                    console.log(res);
                    errorNotification(
                        "There was an issue",
                        `${res.message} [${res.status}] [${res.error}]`,
                        "bottomLeft"
                    )
                });
            }).finally(() => {
                setSubmitting(false);
        })
    }

    const onFinishFailed = () => {
        errorNotification("Something went wrong")
    }

    return <Drawer
        title="Create new person"
        width={720}
        onClose={onCLose}
        visible={showDrawer}
        bodyStyle={{paddingBottom: 80}}
        footer={
            <div
                style={{
                    textAlign: 'right',
                }}
            >
                <Button onClick={onCLose} style={{marginRight: 8}}>
                    Cancel
                </Button>
            </div>
        }
    >
        <Form layout="vertical"
              onFinish={onFinish}
              onFinishFailed={onFinishFailed}
              hideRequiredMark>
            <Row gutter={16}>
                <Col span={12}>
                    <Form.Item
                        name="name"
                        label="Name"
                        rules={[{required: true, message: 'Please enter person name'}]}
                    >
                        <Input placeholder="Please enter person name"/>
                    </Form.Item>
                </Col>
                <Col span={12}>
                    <Form.Item
                        name="email"
                        label="Email"
                        rules={[{required: true, message: 'Please enter person email'}]}
                    >
                        <Input placeholder="Please enter person email"/>
                    </Form.Item>
                </Col>
            </Row>
            <Row gutter={16}>
                <Col span={12}>
                    <Form.Item
                        name="gender"
                        label="gender"
                        rules={[{required: true, message: 'Please select a gender'}]}
                    >
                        <Select placeholder="Please select a gender">
                            <Option value="MALE">MALE</Option>
                            <Option value="FEMALE">FEMALE</Option>
                            <Option value="OTHER">OTHER</Option>
                        </Select>
                    </Form.Item>
                </Col>
            </Row>
            <Row>
                <Col span={12}>
                    <Form.Item >
                        <Button type="primary" htmlType="submit">
                            Submit
                        </Button>
                    </Form.Item>
                </Col>
            </Row>
            <Row>
                {submitting && <Spin indicator={antIcon} />}
            </Row>
        </Form>
    </Drawer>
}

export default PersonDrawerForm;
