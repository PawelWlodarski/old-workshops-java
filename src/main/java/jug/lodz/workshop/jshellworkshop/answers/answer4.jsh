List<User> sortUsers(List<User> us){
    return us.stream().sorted((u1,u2) -> u1.name.compareTo(u2.name)).collect(Collectors.toList());
}
