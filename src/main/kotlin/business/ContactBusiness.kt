package business

import entity.ContactEntity
import repository.ContactRepository

class ContactBusiness {

    private fun validade(name: String, phone: String)
    {
        if(name.isNullOrBlank())
            throw Exception("Nome é obrigatório !")

        if(phone.isNullOrBlank())
            throw Exception("Telefone é obrigatório !")
    }

    private fun validadeRemove(contactEntity: ContactEntity){
        if(contactEntity.name.isNullOrBlank() || contactEntity.phone.isNullOrBlank())
            throw Exception("É necessário selecionar um contato antes de remover")
    }

    fun save(name: String, phone: String){
        validade(name,phone)
        val contact = ContactEntity(name,phone)
        ContactRepository.save(contact)

    }

    fun delete(name: String, phone: String){
        val contactEntity: ContactEntity = ContactEntity(name,phone)
        validadeRemove(contactEntity)
        ContactRepository.delete(contactEntity)
    }

    fun getList(): List<ContactEntity>{
        return ContactRepository.getList()
    }

    fun getContactCount():String{
        val list = getList()
        return "${list.size.toString()} Contatos";
    }

}