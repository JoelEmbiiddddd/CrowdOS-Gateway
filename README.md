# CrowdOS-gateway

CrowdOS-gateway is a component designed to address network communication-related issues in CrowdOS.

---

Tech stack: 

Dubbo, Nacos, Nginx, Netty, Shiro, ZooKeeper.

**Our entire microservice module structure is as follows:**

| Index | System          | Description                                                  | Completion |
| ----- | -------------- | ------------------------------------------------------------ | ---------- |
| 1     | gateway-core   | Gateway core system: used for network communication conversion processing, accepting HTTP requests, calling RPC services. Responsibility chain module call. | √          |
| 2     | gateway-admin  | Gateway management system: used for background management of gateway interface, registration, shutdown and control. | √          |
| 3     | gateway-sdk    | Gateway registration component: used for annotation-based acquisition of interfaces and sending messages to register interfaces. | √          |
| 4     | gateway-center | Gateway registration center: provides gateway registration center services and registers gateway interface information. | √          |
| 5     | gateway-assist | Gateway service center: communication service component encapsulation, used for packaging gateway-core. Communication service component encapsulation, making SpringBoot easier to use. | √          |
| 6     | gateway-engine | Gateway engine project: package the gateway into a jar and run it in the container. | √          |
| 7     | gateway-monitor| Gateway monitoring log: monitoring logs, generating various operation and maintenance reports, automatic alarms, etc. |            |
| 8     | gateway-test   | Gateway test center: used to test the operation of the gateway. | √          |

**Points to consider for improvement and refactorings:**

1. Protocol parsing and conversion, how Netty handles this part, what technology stack are you using? PS: 
2. Service governance; control such as circuit breaking, degradation, flow control, black and white lists. This part can also refer to Istio service mesh.
3. Concurrency volume; how to design and support a large volume, whether it can be horizontally expanded. What are the routing methods?
4. Security management; authentication, risk control, browser fingerprints, etc., and extendable using the SPI mechanism to support business users' plugin expansion.
5. Traffic monitoring; embed some monitoring points, connect with full-link monitoring, such as Dapper, Pinpoint, Prometheus, etc.


**Why did we need to redesign a gateway instead of using an open-source API gateway online?**

Companies such as JD.com, Alibaba, and Meituan have their own API gateways because it can better control the integration of the gateway with their own systems, add various functions they need, and support larger amounts of traffic. However, the maintenance cost of the gateway is also high and requires a relatively large team to maintain and iterate.

---

Please download our test project to verify if each sub-module is running properly: https://github.com/JoelEmbiiddddd/CrowdOS-Gateway-Test
