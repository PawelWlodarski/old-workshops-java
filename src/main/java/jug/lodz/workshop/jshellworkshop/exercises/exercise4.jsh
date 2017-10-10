Consumer<Object> prn=o->System.out.println(o)


class User{
    final String name;

    public User(String name){
        this.name=name;
    }

    @Override
    public String toString(){
        return "User("+name+")";
    }
}

User u1=new User("Roman")
User u2=new User("Agnieszka")
User u3=new User("Zdzislaw")
User u4=new User("BożeBożeBożenka")


List<User> us=List.of(u1,u2,u3,u4)


//implement method sortUsers(us) which returns list of alphabetically sorted users
//remember that comparator is just a two arguments function
void checkExercise4(){
    String[] expected=sortUsers(us).stream().map(u->u.name).toArray();
    prn.accept("sortUsers() : "+Arrays.equals(excpected,
    new String[]{ "Agnieszka", "BożeBożeBożenka", "Roman", "Zdzislaw"}
    ));
}