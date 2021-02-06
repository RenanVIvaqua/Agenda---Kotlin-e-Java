package repository

import entity.ContactEntity
import kotlin.contracts.contract

class ContactRepository {

//  companion object mantem os valores mesmo quando a classe é instaciada novamente
    companion object{
        private val contactList = mutableListOf<ContactEntity>()

        fun save(contactEntity: ContactEntity){
            contactList.add(contactEntity)
        }

        fun delete(contactEntity: ContactEntity){
           var index = 0
            for(item in contactList.withIndex()){
                if(item.value.name == contactEntity.name && item.value.phone == contactEntity.phone ){
                    index = item.index
                    break
                }
            }
            contactList.removeAt(index)
        }

        fun getList(): List<ContactEntity>{
            return  contactList
        }
    }
}