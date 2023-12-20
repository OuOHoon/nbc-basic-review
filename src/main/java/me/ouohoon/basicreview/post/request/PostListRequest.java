package me.ouohoon.basicreview.post.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ouohoon.basicreview.global.dto.SortOption;


import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostListRequest {

    private int page = 0;

    private int size = 5;

    private List<SortOption> sortOptions =
            new ArrayList<>(List.of(new SortOption("createdAt", "desc")));

    public PostListRequest(int page, int size) {
        this.page = page;
        this.size = size;
    }
}
