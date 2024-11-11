package adiantando_codigo.demo.model.dto.user;

import adiantando_codigo.demo.model.domain.users.Student;

public class StudentDTO extends UserDTO
{
    public double balance;
    
    public StudentDTO(Student student)
    {
        super(student);
        this.balance = student.get_balance();
    }
}
