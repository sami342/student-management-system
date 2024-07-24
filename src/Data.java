
public class Data {

 private  String surname;
 private  String given;
 private  String gen;
 private  String address;
 private  String Age;
private  String image;
private String current;
private String date;
private Integer id;
private Integer java;
private Integer netw;
private Integer databas;
private Integer interpr;
private Integer automat;
private Integer phyton;
private Integer total;
private String grade;
 public Data(Integer id,String surname,String Age,String gen,String address,String date,String image,String current ){
     this.id=id;
     this.surname=surname;
     this.gen=gen;
     this.address=address;
     this.Age=Age;
     this.image=image;
    this.date=date;
    this.current=current;
 } 
 public Data(Integer id,String surname,Integer java,Integer netw,Integer databas,Integer interpr,Integer automat,Integer phyton,Integer total,String grade){
     this.id=id;
     this.surname=surname;
     this.java=java;
     this.netw=netw;
     this.databas=databas;
     this.interpr=interpr;
     this.automat=automat;
     this.phyton=phyton;
     this.total=total;
     this.grade=grade;
 }

    public Data(Integer id,String surname, String gen, String current) {
        this.surname = surname;
        this.gen = gen;
        this.current = current;
        this.id = id;
    }
 
 public Data(Integer id,String surname,String given,String gen,String current,String image){
     this.id=id;
     this.surname=surname;
     this.given=given;
     this.gen=gen;
     this.current=current;
     this.image=image;
    
 }
//setter
 
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setGiven(String given) {
        this.given = given;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(String Age) {
        this.Age = Age;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setJava(Integer java) {
        this.java = java;
    }

    public void setNetw(Integer netw) {
        this.netw = netw;
    }

    public void setDatabas(Integer databas) {
        this.databas = databas;
    }

    public void setInterpr(Integer interpr) {
        this.interpr = interpr;
    }

    public void setAutomat(Integer automat) {
        this.automat = automat;
    }

    public void setPhyton(Integer phyton) {
        this.phyton = phyton;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
 
//getter
    public String getSurname() {
        return surname;
    }

    public String getGiven() {
        return given;
    }

    public String getGen() {
        return gen;
    }

    public String getAddress() {
        return address;
    }

    public String getAge() {
        return Age;
    }

    public String getImage() {
        return image;
    }

    public String getCurrent() {
        return current;
    }

    public String getDate() {
        return date;
    }

    public Integer getId() {
        return id;
    }

    public Integer getJava() {
        return java;
    }

    public Integer getNetw() {
        return netw;
    }

    public Integer getDatabas() {
        return databas;
    }

    public Integer getInterpr() {
        return interpr;
    }

    public Integer getAutomat() {
        return automat;
    }

    public Integer getPhyton() {
        return phyton;
    }

    public Integer getTotal() {
        return total;
    }

    public String getGrade() {
        return grade;
    }
 
    
}
