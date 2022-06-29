package com.SafetyNetAlert.SafetyNet.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommunityEmail {

    private String email;

    public CommunityEmail(Person person) {
        this.email = person.getEmail();
    }
}
