package ru.job4j.tsk3_example.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "contact_hobby_detail", schema = "public", catalog = "cars01")
public class ContactHobbyDetailEntity {
   private int contactId;
   private String hobbyId;

   @Basic
   @Column(name = "contact_id", nullable = false, insertable = false, updatable = false)
   public int getContactId() {
      return contactId;
   }

   public void setContactId(int contactId) {
      this.contactId = contactId;
   }

   @Basic
   @Column(name = "hobby_id")
   public String getHobbyId() {
      return hobbyId;
   }

   public void setHobbyId(String hobbyId) {
      this.hobbyId = hobbyId;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      ContactHobbyDetailEntity that = (ContactHobbyDetailEntity) o;

      if (contactId != that.contactId) return false;
      if (hobbyId != null ? !hobbyId.equals(that.hobbyId) : that.hobbyId != null) return false;

      return true;
   }

   @Override
   public int hashCode() {
      int result = contactId;
      result = 31 * result + (hobbyId != null ? hobbyId.hashCode() : 0);
      return result;
   }
}
