# 后端梳理     
## 数据结构     
#### 一元数据结构     
Collection的一元数据：         
	Set，List，Queue，Deque         
Map的二元数据：         
	Map，SortedMap         
今天我们主要看的一元的数据Set，List，Queue，Deque,这些一元的数据，全部的可以看做是数据的变形，无界的队列，基本上都支持自动的扩容。
我们只需要看一下JDK中提供的具体的类型即可：            

set:元素唯一，根据HashMap<E,Object>来进行实现，一元数据的实现根据二元数据的实现的典型案例,所以唯一性的判断依赖于HashMap的判断    
HashSet(无序)，LinkedHashSet(会保存插入的顺序)，TreeSet(**比较的顺序，接口**以及**红黑树**)   

List:元素不唯一，有界和无界，数组实现和链表实现       
ArrayList(数组)，LinkedList(链表) ArrayStack(栈的样式) 全部是线程非安全的 ，Vector(线程安全的，方法上面加入关键字)      

Queue: 先进先出的数据结构

