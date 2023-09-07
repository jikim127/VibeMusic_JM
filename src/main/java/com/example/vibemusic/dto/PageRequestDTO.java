package com.example.vibemusic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.net.URLEncoder;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    public void PageRequestDTO(int page, int size, String type, String keyword){
        this.page = page;
        this.size = size;
        this.type = type;
        this.keyword = keyword;
    }

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 12;

    private String type;
    private String keyword;

    public String[] getTypes() {
        if(type == null || type.isEmpty()){
            return null;
        }
        return type.split("");
    }

    public Pageable getPageable(String ...props){
        return PageRequest.of(this.page -1,this.size, Sort.by(props).descending());
    }

    private String link;

    public String getLink() {

        if(link == null){
            StringBuilder builder = new StringBuilder();
            builder.append("page="+this.page);
            builder.append("&size="+this.size);

            if(type != null && type.length() >0){
                builder.append("&type="+type);
            }

            if(keyword != null){
                try{
                    builder.append("&keyword="+ URLEncoder.encode(keyword,"utf-8"));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            link = builder.toString();

        }
        return link;
    }

}
