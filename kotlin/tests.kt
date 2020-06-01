fun main() {
//    val n = Man("小李")
////    n.吃饭()
//    n.abc()
//
    add(1, 3)
    abc()

}


// 一个函数
fun echo() {
    println("吃饭")
}

// 有参数的函数
fun add(a: Int, b: Int) {
    println(a + b)
}

// 有参数和返回值的函数
fun addE(a: Int, b: Int): Int {
    return a + b
}

// 有参数和返回值的函数
fun addEx() {
    println("adsf")
}

// 有参数和返回值的函数
fun abc(a: Int = 5, b: Int = 6) {
    println(a+b)
}


//匿名函数
//val a = fun(a: Int): String {
//    return a.toString()
//}
val a: (Int) -> String = {
    it.toString()
}


/*

匿名函数变形
java
    public interface OnClickListener{     //1 定义一个接口  实际上这个接口只为了实现一个函数的调用
        void onClick(View v);
    }
    public void setOnClickListener(OnClickListener listener){    //2 包装一个对象
        this.listener=listener;
    }
    view.setOnClickListener(new OnClickListener(){      //3 调用 2
        //参数1 接受一个对象 这里new了一个接口 作为包装对象来实现 调用函数 onClick
        @Override           重写方法
        void onClick(View v){
            switchToNextPage();
        }

    })

kotlin
    fun setOnClickListener(onClick: (View) -> Unit) {
        // 1 这里实现了java的第1、2步骤
        // 参数1 接收一个函数 使用匿名函数创建函数体
        this.onClick = onClick
    }
    view.setOnClickListener(fun(v: View): Unit {      //2 使用
        switchToNextPage()
    })
*/