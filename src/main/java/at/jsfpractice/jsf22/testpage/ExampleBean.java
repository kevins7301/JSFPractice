package at.jsfpractice.jsf22.testpage;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="exampleBean")
public class ExampleBean {
     
    private List<MyData> dataList;
    /**
     * @return the dataList
     */
    public List<MyData> getDataList() {
         
        if(dataList == null){
            dataList = getMyDataList();
        }
        return dataList;
    }
 
    /**
     * @param dataList the dataList to set
     */
    public void setDataList(List<MyData> dataList) {
        this.dataList = dataList;
    }
     
    public List<MyData> getMyDataList(){
         
    dataList = new ArrayList<MyData>();
                 MyData myData1 = new MyData();
                myData1.setId("1");
                myData1.setName("AA");
                dataList.add(myData1);
         
        MyData myData2 = new MyData();
        myData2.setId("2");
        myData2.setName("BB");
        dataList.add(myData2);
         
        MyData myData3 = new MyData();
        myData3.setId("3");
        myData3.setName("CC");
        dataList.add(myData3);
         
        MyData myData4 = new MyData();
        myData4.setId("4");
        myData4.setName("DD");
        dataList.add(myData4);
         
        MyData myData5 = new MyData();
        myData5.setId("5");
        myData5.setName("EE");
        dataList.add(myData5);
         
        MyData myData6 = new MyData();
        myData6.setId("6");
        myData6.setName("FF");
        dataList.add(myData6);
         
        MyData myData7 = new MyData();
        myData7.setId("7");
        myData7.setName("GG");
        dataList.add(myData7);
         
        return dataList;
    }
}