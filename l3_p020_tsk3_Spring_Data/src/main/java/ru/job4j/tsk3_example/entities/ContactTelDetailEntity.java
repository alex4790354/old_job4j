package ru.job4j.tsk3_example.entities;

import javax.persistence.*;

@Entity
@Table(name = "contact_tel_detail", schema = "public", catalog = "cars01")
public class ContactTelDetailEntity {
   private int id;
   private int contactId;
   private String telType;
   private String telNumber;
   private int version;

   @Id
   @Column(name = "id")
   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   @Basic
   @Column(name = "contact_id")
   public int getContactId() {
      return contactId;
   }

   public void setContactId(int contactId) {
      this.contactId = contactId;
   }

   @Basic
   @Column(name = "tel_type")
   public String getTelType() {
      return telType;
   }

   public void setTelType(String telType) {
      this.telType = telType;
   }

   @Basic
   @Column(name = "tel_number")
   public String getTelNumber() {
      return telNumber;
   }

   public void setTelNumber(String telNumber) {
      this.telNumber = telNumber;
   }

   @Basic
   @Column(name = "version")
   public int getVersion() {
      return version;
   }

   public void setVersion(int version) {
      this.version = version;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      ContactTelDetailEntity that = (ContactTelDetailEntity) o;

      if (id != that.id) return false;
      if (contactId != that.contactId) return false;
      if (version != that.version) return false;
      if (telType != null ? !telType.equals(that.telType) : that.telType != null) return false;
      if (telNumber != null ? !telNumber.equals(that.telNumber) : that.telNumber != null) return false;

      return true;
   }

   @Override
   public int hashCode() {
      int result = id;
      result = 31 * result + contactId;
      result = 31 * result + (telType != null ? telType.hashCode() : 0);
      result = 31 * result + (telNumber != null ? telNumber.hashCode() : 0);
      result = 31 * result + version;
      return result;
   }
}
