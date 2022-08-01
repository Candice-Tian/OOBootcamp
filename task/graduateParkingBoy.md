# Story（Calarification Needed）

**作为** 银卡VIP用户
**我想要** 一个初入职场的停车小弟来帮我停车和取车
**从而** 节省我的时间

---

# Tasking（Business-Oriented Tasking）
### AC 1：当停车场有空闲车位时，申请停车，停车成功，取到停车票
- Example 1
    -  Given 共一个停车场共30个车位，已停车辆为0， When 申请停车A时, Then 停车成功，空闲车位为29，返回{"code":"停车成功",ticket:{"carNum":"A"，“parkNum”:1}}
- Example 2
    -  Given 共两个停车场各2个车位，停车场1车位已满，停车场2已停车辆为0， When 申请停车A时, Then 停车成功，停车场2空闲车位变更为1，返回{"code":"停车成功",ticket:{"carNum":"A"，“parkNum”:2}}
- Example 3
    -  Given 共两个停车场各2个车位，停车场1已停车辆为0，停车场2已停车辆为1， When 申请停车A时, Then 停车成功，停车场1空闲车位变更为1，返回{"code":"停车成功",ticket:{"carNum":"A"，“parkNum”:1}}

### AC 2：当停车场没有空闲车位时，申请停车，停车失败，返回用户失败原因
- Example
    -  Given 共两个停车场各2个车位，停车场1、2车位均已满，When 申请停车时, Then 停车失败，返回{"code":"停车失败，车位已满", "ticket":null}

### AC 3：当车辆在停车场时，且停车票有效，申请取车，取车成功
- Example
    - Given 停车票{carNum:"A", "parkNum":1}有效, When 使用该停车票申请取车A时, Then 取车成功，停车场1的空闲车位+1

### AC 4：当停车票无效，申请取车，取车失败，返回失败原因
- Example
    - Given 停车票{carNum:"A", "parkNum":1}，但是车辆A不在停车场1， When使用该停车票申请取车A, Then 取车失败，返回{"code":"取车失败, 停车票无效", "ticket":null}