package at.fhv.mme.bt_dementia_app.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import at.fhv.mme.bt_dementia_app.R
import at.fhv.mme.bt_dementia_app.databinding.ItemContactBinding
import at.fhv.mme.bt_dementia_app.model.Contact

class ContactListAdapter(
    private val contactList: List<Contact>
) : RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemContactBinding.bind(itemView)

        fun databind(contact: Contact) {
            binding.tvName.text = contact.name
            binding.tvRelation.text = contact.relation
        }
    }

    /**
     * creates and returns a ViewHolder object, inflating a standard layout called item_contact
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        )
    }

    /**
     * returns the size of the list
     */
    override fun getItemCount(): Int {
        return contactList.size
    }

    /**
     * called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(contactList[position])
    }
}

