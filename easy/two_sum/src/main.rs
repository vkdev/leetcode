fn main() {
    let nums: Vec<i32> = [3,2,4].to_vec();

    let target:i32 = 6;

    let result = Solution::two_sum(nums, target);
    let string_result = result.into_iter().map(|i| i.to_string() + " ").collect::<String>();
    
    println!("Result");
    println!("{}", string_result);
}

struct Solution{}

impl Solution {

    fn two_sum(nums: Vec<i32>, target: i32) -> Vec<i32> {
        let nums_size = nums.len();

        for index1 in 0..nums_size  {
            let det = target - nums[index1];
            for index2 in (index1 + 1)..nums_size  {
                if nums[index2] == det {
                    return  [index1 as i32, index2 as i32].to_vec();
                }
            };
        };
        
        return Vec::new();
    }
}